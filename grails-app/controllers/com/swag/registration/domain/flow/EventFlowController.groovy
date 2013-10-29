package com.swag.registration.domain.flow

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

import com.swag.registration.domain.Event
import com.swag.registration.domain.RegistrationLevel
import com.swag.registration.security.User
import com.swag.registration.security.acl.EventService
import grails.plugins.springsecurity.SpringSecurityService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class EventFlowController {
    EventService eventService
    SpringSecurityService springSecurityService

    def index() {
        redirect(action: "createEvent")
    }

    def createEventFlow = {
        start {
            action {
                flow.eventData = [:]
                flow.eventData["registrationLevels"] = []
                if (springSecurityService.loggedIn) {
                    conversation.user = springSecurityService.currentUser
                    alreadyLoggedIn()
                } else {
					success()
                }
            }
            on ("success").to "login"
            on ("alreadyLoggedIn").to "basicInfo"
        }
		
		login {
			on ("login").to "authenticate"
			on ("createUser").to "createUser"
		}
		
		authenticate {
			action {
				User user = User.findByEmailAddress(params.emailAddress)
				if (user && user.password == springSecurityService.encodePassword(params.password)) {
					conversation.user = user
					springSecurityService.reauthenticate(user.username, params.password)
					success()
				} else {
					flash.message = "Username or password is incorrect!"
					error()
				}
			}
			
			on ("error").to "login"
			on ("success").to "basicInfo"
		}

        createUser {
            subflow(controller: "userFlow", action: "createUser", input: [sub: true])
            on ("success").to "login"
        }

        basicInfo {
            on ("next") {
                flow.eventData["name"] = params.name
                flow.eventData["homePage"] = params.homePage
                flow.eventData["year"] = params.year
                flow.eventData["numeral"] = params.numeral
                session["flow"] = flow
            }.to "merchantInfo"
            on ("error").to "basicInfo"
        }

        merchantInfo {
            on ("next") {
                flow.eventData["merchantEmail"] = params.merchantEmail
                flow.eventData["currency"] = params.currency
                flow.eventData["taxRate"] = params.taxRate
                session["flow"] = flow
            }.to "registrationLevel"
            on ("error").to "merchantInfo"
        }

        registrationLevel {
            on ("addAnother") {
                flow.eventData["registrationLevels"] += [name: params.name, description: params.description, price: params.price, validFor: params.validFor]
            }.to "registrationLevel"
            on ("done") {
                flow.eventData["registrationLevels"] += [name: params.name, description: params.description, price: params.price, validFor: params.validFor]
            }.to "confirm"
        }

        confirm {
			on ("editBasicInfo").to "basicInfo"
			on ("editMerchantInfo").to "merchantInfo"
			on ("editRegistrationInfo").to "registrationLevel"
            on ("confirm").to "finish"
            on ("error").to "confirm"
        }

        finish {
            action {
                Event event = eventService.create(flow.eventData)
                flow.eventData["registrationLevels"].each {
                    RegistrationLevel level = new RegistrationLevel(it)
                    level.save()
                    event.addToLevels(level)
                }
                redirect (controller: "event", action: "show", id: event.id)
            }
            on ("success").to "end"
        }

        end() {
			
		}
    }
}
