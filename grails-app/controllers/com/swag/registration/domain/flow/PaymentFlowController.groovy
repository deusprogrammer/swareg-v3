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

class PaymentFlowController {
    PaymentService paymentService
    SpringSecurityService springSecurityService

    def paymentFlow = {
        start {
            action {
                println "In start()"
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
					existingUser()
				} else {
					success()
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
					springSecurityService.reauthenticate(user.username, params.password)
					success()
				} else {
					flash.message = "Username or password is incorrect!"
					error()
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
				
				Event found = user.registrations.find { Registration reg ->
					reg.event == event
				}
				
				if (found && !found.paid) {
					return alreadyRegistered()
				} else if (found && found.paid) {
					return alreadyRegisteredAndPaid()
				} else {
					return success()
				}
			}
			on ("alreadyRegistered").to "pay"
			on ("alreadyRegisteredAndPaid").to "alreadyPaid"
			on ("success").to "register"
		}

        register {
            on ("continue") {
                if (!params.regLevelId) {
                    flash.message = "You must choose a registration level!"
                }

                flow.regLevelId = params.regLevelId
                flow.regLevel = RegistrationLevel.get(flow.regLevelId)
            }.to "pay"
        }

        pay {
            on ("continue") {
                flow.ccData["creditCardNumber"] = params.creditCardNumber
                flow.ccData["cvv2"] = params.cvv2
                flow.ccData["expireMonth"] = params.expireMonth
                flow.ccData["expireYear"] = params.expireYear
                flow.ccData["type"] = params.type
            }.to "confirmRegistration"
		
			on ("payLater") {
				flow.payLater = true
			}.to "payLater"
        }

        confirmRegistration {
            on ("confirm").to "process"
        }

        process {
			action {
				Registration reg = new Registration(registrationLevel: flow.regLevel, user: conversation.user, event: flow.event)
				if (!reg.save()) {
					flash.message = "Unable to create registration"
					println "REGISTRATION FUCKED UP!"
					reg.errors.each {
						println it
					}
					return error()
				}

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
                    flash.message = "There was an error with processing your payment"
					error()
                }
            }
            on ("success").to "finish"
            on ("error").to "pay"
        }
		
		payLater {
			action {
				Registration reg = new Registration(registrationLevel: flow.regLevel, user: conversation.user, event: flow.event)
				if (!reg.save()) {
					flash.message = "Unable to create registration"
					println "REGISTRATION FUCKED UP!"
					reg.errors.each {
						println it
					}
					flash.message = "Registration failed!"
					flash.errors = reg.errors
					return error()
				} else {
					return success()
				}
			}
			on "success".to "finish"
			on "error".to "pay"
		}

        finish {

        }

    }
}
