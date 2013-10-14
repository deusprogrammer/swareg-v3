package com.swag.registration.domain.flow

import com.swag.registration.security.Role
import com.swag.registration.security.UserRole
import com.swag.registration.security.User
import grails.plugins.springsecurity.SpringSecurityService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.apache.commons.lang.RandomStringUtils

class UserFlowController {
    SpringSecurityService springSecurityService

    def createUserFlow = {
        input {
            sub(false)
        }

        start {
            action {
                flow.userId = 0
                flow.shippingData = [:]
                flow.billingData = [:]
                flow.userData = [:]
				flow.userData["emailAddress"] = conversation.emailAddress
            }
            on("success").to "enterUserData"
        }

        enterUserData {
            on ("next").to "processUserData"
        }
		
		processUserData {
			action {
				if (params.password1 != params.password2) {
					flash.message = "Passwords do not match!"
					return error()
				}
				
				flow.userData["username"] = params.emailAddress
				flow.userData["password"] = params.password1
				flow.userData["emailAddress"] = params.emailAddress
				flow.userData["age"] = params.age
				flow.userData["gender"] = params.gender
				flow.userData["firstName"] = params.firstName
				flow.userData["lastName"] = params.lastName
			}
			on ("success").to "enterShipping"
			on ("error").to "enterUserData"
		}

        enterShipping {
            on ("next") {
                flow.shippingData["streetAddress1"] = params.streetAddress1
                flow.shippingData["streetAddress2"] = params.streetAddress2
                flow.shippingData["city"] = params.city
                flow.shippingData["state"] = params.state
                flow.shippingData["zipCode"] = params.zipCode
            }.to "enterBilling"
            on ("back") {
                [flow: flow]
            }.to "enterUserData"
        }

        enterBilling {
            on ("next") {
                if (params.sameAsShipping) {
                    flow.billingData = flow.shippingData
                    flow.billingData["sameAsShipping"] = true
                } else {
                    flow.billingData["streetAddress1"] = params.streetAddress1
                    flow.billingData["streetAddress2"] = params.streetAddress2
                    flow.billingData["city"] = params.city
                    flow.billingData["state"] = params.state
                    flow.billingData["zipCode"] = params.zipCode
                    flow.billingData["sameAsShipping"] = params.sameAsShipping
                }
            }.to "confirm"
            on ("back").to "enterShipping"
        }

        confirm {
            on ("confirm").to "saveUser"
			on ("editUserInfo").to "enterUserData"
			on ("editShippingInfo").to "enterShipping"
			on ("editBillingInfo").to "enterBilling"
            on ("back").to "enterBilling"
        }
		
		saveUser {
			action {
				User user = new User(flow.billingData + flow.userData)
				
				if (!user.save()) {
					println "ERRORS:"
					String errors = "Errors: "
					user.errors.each {
						errors += "\n${it}"
					}
					
					flash.message = errors
					error()
				} else {
					Role roleUser = Role.findByAuthority('ROLE_USER')
					UserRole.create user, roleUser, true
					conversation.user = user
					success()
				}
			}
			on ("success").to "finish"
			on ("error").to "confirm"
		}

        finish {
            action {
				// Send email about user creation
				
                if (flow.sub) {
                    success()
                } else {
                    done()
                }
            }
            on ("success").to "success"
            on ("done").to "done"
        }

        done {
            action {
                redirect(controller: "user", action: "show", id: flow.userId)
            }
            on ("success").to "end"
        }

        end() {
			
		}

        success () {
			
		}
    }
}
