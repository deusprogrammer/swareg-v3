package com.swag.registration.domain.flow

import com.sun.org.apache.xerces.internal.impl.xs.traversers.OneAttr
import com.swag.registration.domain.*
import com.swag.registration.domain.order.*
import com.swag.registration.security.User

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

import grails.converters.JSON
import grails.plugins.springsecurity.SpringSecurityService

class RegistrationFlowController {
    OrderService orderService
    SpringSecurityService springSecurityService

	def manualRegistrationFlow = {
		start {
			action {
				flow.eventId = params.id
				flow.event = Event.findByUuid(flow.eventId)
				
				if (!flow.eventId || !flow.event) {
					return noEvent()
				}
				
				// Do checks on current user to see if they are admin on this event
			}
			on ("noEvent").to "handleError"
			on ("accessDenied").to "handleError"
			on ("success").to "createManualRegistration"
		}
		
		createManualRegistration {
			on ("submit") {
				flow.emailAddress = params.emailAddress
                flow.regLevelId = params.regLevelId
			}.to "processManualRegistration"
		}
		
		processManualRegistration {
			action {
				flow.regLevel = RegistrationLevel.get(flow.regLevelId)
				User user = User.findByEmailAddress(flow.emailAddress)
				
				if (!flow.regLevel) {
					flash.message = "Registration level not found!"
					return error()
				}
				
				if (!user) {
					user = new User(emailAddress: flow.emailAddress, password: RandomStringUtils.random(16))
					if (!user.save(flush: true)) {
						flash.message = "User creation failed"
						flash.errors = user.errors
						return error()
					}
				}
				
				Registration reg = new Registration(registrationLevel: flow.regLevel, user: user, event: flow.event, paid: true)
				if (!reg.save(flush: true)) {
					flash.message = "Registration failed!"
					flash.errors = reg.errors
					return error()
				}
				
				return success()
			}
			on ("success").to "finish"
			on ("error").to "createManualRegistration"
		}
		
		finish {
			
		}
		
		handleError {
			
		}
	}

    def registrationFlow = {
        start {
            action {
                flow.userId = 0
                flow.eventId = params.id
                flow.ccData = [:]
                flow.regLevelId = 0
                flow.receipt = ""
				
				if (!params.id) {
					return noEvent()	
				}

				flow.event = Event.findByUuid(flow.eventId)
				
                if (springSecurityService.loggedIn) {
                    conversation.user = springSecurityService.currentUser
                    return alreadyLoggedIn()
                }
            }
            on ("success").to "choose"
            on ("alreadyLoggedIn").to "checkRegistration"
            on ("noEvent").to "chooseEvent"
        }
		
		chooseEvent {
			on ("select") {
				flow.eventId = params.event
				flow.event = Event.get(params.event)
			}.to "choose"
		}

        choose {
            on ("continue") {
				conversation.emailAddress = params.emailAddress
			}.to "processEmail"
        }
		
		processEmail {
			action {
				User user = User.findByEmailAddress(conversation.emailAddress)
				if (user) {
					return existingUser()
				} else {
					return success()
				}
			}
			on ("success").to "createUser"
			on ("existingUser").to "login"
		}

        login {
            on ("login").to "authenticate"
            on ("createUser").to "createUser"
        }
		
		authenticate {
			action {
				User user = User.findByEmailAddress(conversation.emailAddress)
				if (user.password == springSecurityService.encodePassword(params.password)) {
					conversation.user = user
					return success()
				} else {
					flash.message = "Username or password is incorrect!"
					return error()
				}
			}
			
			on ("error").to "login"
			on ("success").to "createOrder"
		}

        createUser {
            subflow(controller: "userFlow", action: "createUser", input: [sub: true, isRegistration: true])
            on ("success").to "createOrder"
        }

        createOrder {
            on ("continue") {
                println "PARAMS: ${params}"
				
				// Get number in textfield and create an order object
				PayPalOrder order = new PayPalOrder([event: flow.event])
				params.levels.each { key, value ->
					if (key.isNumber()) {
						println "${key} => ${value.quantity}"
						order.addToBadges(new RegistrationOrderItem([
							registrationLevel: RegistrationLevel.get(key.toLong()),
							quantity: value.quantity
						]))
					}
				}
				
				flow.order = order
            }.to "paypalPay"
        }
		
		processOrder {
			action {
				if (!flow.order.validate()) {
					flash.message = "Unable to validate order!"
					return error()
				}
			}
			on("success").to "paypalPay"
			on("error").to "createOrder"
		}
				
		paypalPay {
			on ("continue").to "processPayPalPayment"
		}
		
		processPayPalPayment {
			action {
				PayPalOrder order = flow.order
				Event event = flow.event
				String transactionId = order.transactionId
				User user = conversation.user
				
				String returnUrl = createLink(absolute: true, action: "completePayPal", params: [transaction: transactionId])
				String cancelUrl = createLink(absolute: true, action: "cancelPayPal")
				
				Map paymentResults = orderService.payWithPayPal(order, returnUrl, cancelUrl)

				if (paymentResults["success"]) {
					order.user = user
					order.save()

					println "Redirecting to ${paymentResults['redirectUrl']}"
					redirect(url: paymentResults['redirectUrl'])
				} else {
					flash.message = "${paymentResults['error']['message']}<br>Details:<br>${paymentResults['error']['details'] ?: ''}"
					return error()
				}
			}
			on ("success").to "end"
			on ("error").to "paypalPay"
		}

        finish {

        }
		
		end {
			
		}
    }
	
	def completePayPal() {
		PayPalOrder order = PayPalOrder.findByTransactionId(params.transaction)
		
		if (!order) {
			log.error("Unable to find a transaction with id ${params.transactionId}")
			flash.message = "Unable to find a transaction with id ${params.transactionId}"
			return [order: order]
		} else {
			log.info("Found payment!")
		}
		
		Map paymentResults = orderService.executePayPalPayment(order, params.PayerID)
		
		println "RESULTS: ${paymentResults}"
		
		if (paymentResults["success"]) {
			order.paymentCompleted = true
			order.paymentStatus = paymentResults["status"]
			order.transactionId = null
			order.generateRegistrations()
			order.save()
			
			// Update user with shipping info returned from PayPal
			User user = order.user
			user.streetAddress1 = paymentResults["shipping"]["line1"]
			user.streetAddress2 = paymentResults["shipping"]["line2"]
			user.city = paymentResults["shipping"]["city"]
			user.state = paymentResults["shipping"]["state"]
			user.zipCode = paymentResults["shipping"]["zipCode"]
			user.countryCode = paymentResults["shipping"]["countryCode"]
			user.save()
			
			return [order: order]
		} else {
			flash.message = "${paymentResults['error']['message']}<br>Details:<br>${paymentResults['error']['details'] ?: ''}"
			return [order: order]
		}
	}
	
	def processPayPal() {
		
	}
	
	def cancelPayPal() {
		
	}
}
