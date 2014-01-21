package com.swag.registration.domain.flow

import com.sun.org.apache.xerces.internal.impl.xs.traversers.OneAttr
import com.swag.registration.EmailService
import com.swag.registration.domain.*
import com.swag.registration.domain.order.*
import com.swag.registration.security.Activation
import com.swag.registration.security.User
import com.swag.registration.security.acl.EventService
import com.trinary.paypal.error.exception.PayPalException

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

import grails.converters.JSON
import grails.plugins.springsecurity.SpringSecurityService

class RegistrationFlowController {
	EventService eventService
	EmailService emailService
    OrderService orderService
    SpringSecurityService springSecurityService

    def manualRegistrationFlow = {
        start {
            action {
                flow.eventId = params.id
                Event event = Event.get(params.id)
				flow.event = event

                if (!event) {
                    return noEvent()
                }
				
				println "IN START!"
				println "EVENT: ${event}"

                // Do checks on current user to see if they are admin on this event
				/*
				try {
					eventService.checkAdmin(event)
				} catch (Exception e) {
					println "ACCESS FUCKING DENIED!"
					println "EXCEPTION: ${e.message}"
					return accessDenied()
				}
				*/
				if (!event.user == springSecurityService.currentUser) {
					println "ACCESS FUCKING DENIED!"
					return accessDenied()
				}
            }
            on ("noEvent").to "errorMR"
            on ("accessDenied").to "errorMR"
            on ("success").to "createManualRegistration"
        }

        createManualRegistration {
            on ("continue") {
                flow.emailAddress = params.emailAddress
                flow.regLevelId = params.tier
            }.to "processManualRegistration"
        }

        processManualRegistration {
            action {
                RegistrationLevel regLevel = RegistrationLevel.get(flow.regLevelId)
				Event event = Event.get(flow.eventId)
                User user = User.findByEmailAddress(flow.emailAddress)

                if (!regLevel) {
                    flash.message = "Registration level not found!"
                    return error()
                }

                if (!user) {
					Activation activation = Activation.create(flow.emailAddress)
                    user = activation.user
                }

                Registration reg = new Registration(registrationLevel: regLevel, user: user, event: event, paid: true)
                if (!reg.save(flush: true)) {
                    flash.message = "Registration failed!"
                    flash.errors = reg.errors
                    return error()
                }

                return success()
            }
            on ("success").to "finishMR"
            on ("error").to "createManualRegistration"
        }

        finishMR {
			action {
				redirect(controller: "dashboard", action: "index")
			}
			on ("success").to "endMR"
        }
		
		endMR {
			
		}

        errorMR {

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
                    if (key.isNumber() && value.quantity && value.quantity > 0) {
                        println "${key} => ${value.quantity}"
						RegistrationLevel rl = RegistrationLevel.get(key.toLong())
						if (!rl.needAdmin) {
	                        order.addToBadges(new RegistrationOrderItem([
	                            registrationLevel: rl,
	                            quantity: value.quantity
	                        ]))
						}
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

                String returnUrl = createLink(absolute: true, action: "completeRegistration", params: [transaction: transactionId])
                String cancelUrl = createLink(absolute: true, action: "cancelPayPal")

				Map paymentResults
				
				try {
					paymentResults = orderService.payWithPayPal(order, returnUrl, cancelUrl)
				} catch (PayPalException e) {
					flash.message = "${e.map.message}<br>Details:<br>${e.map.details ?: ''}"
					return error()
				} catch (Exception e) {
					flash.message = "Unexpected error occured during PayPal transaction!  Please contact webmaster at swag.expo@gmail.com with exception message below.<br>Exception: ${e.message}"
					return error()
				}

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
	
	def completeRegistrationFlow = {
		start {
			action {
				flow.transaction = params.transaction
				flow.payerId = params.PayerID
				PayPalOrder order = PayPalOrder.findByTransactionId(params.transaction)
				flow.order = order
				
				if (!order) {
					log.error("Unable to find a transaction with id ${flow.transaction}")
					flash.message = "Unable to find a transaction with id ${flow.transaction}"
					error()
				}
			}
			on ("success").to "confirmCR"
			on ("error").to "errorCR"
		}
		
		confirmCR {
			on ("confirm").to "completeCR"
			on ("cancel").to "cancelCR"
		}
		
		completeCR {
			action {
				PayPalOrder order = PayPalOrder.findByTransactionId(flow.transaction)
				
				Map paymentResults = orderService.executePayPalPayment(order, flow.payerId)
		
				println "RESULTS: ${paymentResults}"
		
				if (paymentResults["success"]) {
					order.paymentCompleted = true
					order.paymentStatus = paymentResults["status"]
					order.transactionId = null
					order.generateRegistrations()
					order.save()
					
					emailService.sendOrderEmail(order)
		
					// Update user with shipping info returned from PayPal
					User user = order.user
					user.streetAddress1 = paymentResults["shipping"]["line1"]
					user.streetAddress2 = paymentResults["shipping"]["line2"]
					user.city = paymentResults["shipping"]["city"]
					user.state = paymentResults["shipping"]["state"]
					user.zipCode = paymentResults["shipping"]["zipCode"]
					user.countryCode = paymentResults["shipping"]["countryCode"]
					user.save()
				} else {
					return error()
				}
			}
			on ("success").to "finishCR"
			on ("error").to "errorCR"
		}
		
		cancelCR {
			on ("confirm").to "goHomeCR"
		}
		
		errorCR {
			
		}
		
		finishCR {
			on ("confirm").to "goHomeCR"
		}
		
		goHomeCR {
			action {
				redirect(controller: "dashboard", action: "index")
			}
			on ("success").to "endCR"
		}
		
		endCR {
			
		}
	}

    def cancelPayPal() {
		chain(controller: "dashboard", action: "index")
    }
}
