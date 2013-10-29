package com.swag.registration.domain.flow

import com.sun.org.apache.xerces.internal.impl.xs.traversers.OneAttr
import com.swag.registration.domain.Event
import com.swag.registration.domain.Payment
import com.swag.registration.domain.PaymentService
import com.swag.registration.domain.Registration
import com.swag.registration.domain.RegistrationLevel
import com.swag.registration.security.User

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

import grails.converters.JSON
import grails.plugins.springsecurity.SpringSecurityService

class RegistrationFlowController {
    PaymentService paymentService
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
				
				return success()
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
			on ("success").to "checkRegistration"
		}

        createUser {
            subflow(controller: "userFlow", action: "createUser", input: [sub: true])
            on ("success").to "checkRegistration"
        }
		
		checkRegistration {
			action {
				User user = conversation.user
				Event event = flow.event
				
				Registration found = user.registrations.find { Registration registration ->
					registration.event == event
				}
				
				if (found) {
					flow.registration = found
				}
				
				if (found && !found.paid) {
					return alreadyRegistered()
				} else if (found && found.paid) {
					return alreadyPaid()
				} else {
					return success()
				}
			}
			on ("alreadyRegistered").to "resume"
			on ("alreadyPaid").to "alreadyPaid"
			on ("success").to "register"
		}

        register {
            on ("continue") {
                if (!params.regLevelId) {
                    flash.message = "You must choose a registration level!"
                }

                flow.regLevelId = params.regLevelId
                flow.regLevel = RegistrationLevel.get(flow.regLevelId)
            }.to "processRegistration"
        }
		
		processRegistration {
			action {
				Registration reg = new Registration(registrationLevel: flow.regLevel, user: conversation.user, event: flow.event)
				if (!reg.save()) {
					reg.errors.each {
						println it
					}
					flash.message = "Registration failed!"
					flash.errors = reg.errors
					return error()
				}
				
				flow.registration = reg
			}
			on("success").to "choosePayment"
			on("error").to "register"
		}
		
		resume {
			on ("resume").to "choosePayment"
			on ("startOver"){
				flow.registration.delete()
			}.to "register"
		}
		
		choosePayment {
			on ("paypal").to "paypalPay"
			on ("credit").to "creditPay"
			on ("payLater").to "payLater"
		}

        creditPay {
            on ("continue") {
                flow.ccData["creditCardNumber"] = params.creditCardNumber
                flow.ccData["cvv2"] = params.cvv2
                flow.ccData["expireMonth"] = params.expireMonth
                flow.ccData["expireYear"] = params.expireYear
                flow.ccData["type"] = params.type
            }.to "confirmCreditCardPayment"
        }

        confirmCreditCardPayment {
            on ("confirm").to "processCreditCardPayment"
        }

        processCreditCardPayment {
			action {
				Registration reg = flow.registration
				Map paymentResults = paymentService.payWithCreditCard(reg, flow.ccData["creditCardNumber"], flow.ccData["cvv2"], flow.ccData["expireMonth"], flow.ccData["expireYear"], flow.ccData["type"])

				if (paymentResults["success"]) {
					flow.receipt = paymentResults["receiptNumber"]
					Payment payment = new Payment(creditCardNumber: paymentResults["ccNumber"], paymentId: paymentResults["receiptNumber"], status: paymentResults["status"])
					payment.save()
					reg.payment = payment
					reg.paid = true
					reg.save()
                } else {
					reg.delete()
                    flash.message = "${paymentResults['error']['message']}<br>Details:<br>${paymentResults['error']['details'] ?: ''}"
					return error()
                }
            }
            on ("success").to "finish"
            on ("error").to "creditPay"
        }
		
		payLater {
			on ("ok").to "finish"
			on ("wait").to "choosePayment"
		}
		
		alreadyPaid {
			on ("yes").to "register"
			on ("no").to "finish"
		}

        finish {

        }

    }
}
