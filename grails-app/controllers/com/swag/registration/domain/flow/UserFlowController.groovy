package com.swag.registration.domain.flow

import com.swag.registration.security.Role
import com.swag.registration.security.UserRole
import com.swag.registration.security.User
import grails.plugins.springsecurity.SpringSecurityService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.apache.commons.lang.RandomStringUtils

import org.apache.commons.lang.RandomStringUtils

class UserFlowController {
    SpringSecurityService springSecurityService

    def login() {
        User user = User.findByEmailAddress(params.emailAddress)
        if (user && user.password == springSecurityService.encodePassword(params.password)) {
            springSecurityService.reauthenticate(user.username, params.password)
        } else {
            flash.message = "Username or password is incorrect!"
        }
        redirect(uri: "/")
    }

    def requestPasswordReset(long id) {
        User user = User.get(id)
        user.resetToken = UUID.randomUUID()
        user.save()

        // Email that reset token and a link to the user
    }

    def resetPassword(long id) {
        String token = id

        User user = User.findByResetToken(token)
        user.resetToken = null
        user.password = RandomStringUtils.random(9, true, true)
        user.save()

        // Email that password to the user's email address
    }

    def changePasswordFlow = {
        start {
            action {
                flow.emailAddress = params.emailAddress

                flow.user = User.findByEmailAddress(flow.emailAddress)

                if (!flow.user) {
                    return invalidEmail()
                } else {
                    return success()
                }
            }
            on ("success").to "changePassword"
            on ("invalidEmail").to "enterEmailAddress"
        }

        enterEmailAddress {
            on ("go") {
                flow.emailAddress = params.emailAddress
            }.to "processEmailAddress"
        }

        processEmailAddress {
            action {
                flow.user = User.findByEmailAddress(flow.emailAddress)
                if (!flow.user) {
                    flash.message = "No user with email address ${flow.emailAddress} exists!"
                    return invalidEmail()
                } else {
                    return success()
                }
            }
            on ("invalidEmail").to "enterEmailAddress"
            on ("success").to "changePassword"
        }

        changePassword {
            on ("changePassword") {
                flow.oldPassword = params.oldPassword
                flow.password1 = params.password1
                flow.password2 = params.password2
            }.to "processChangePassword"
        }

        processChangePassword {
            action {
                User user = flow.user
                if (user.password != springSecurityService.encodePassword(flow.oldPassword)) {
                    flash.message = "Invalid password for email address ${flow.emailAddress}"
                    return invalidLogin()
                }

                if (flow.password1 != flow.password2) {
                    flash.message = "Password and confirmation password don't match!"
                    return passwordMismatch()
                } else {
                    user.password = params.password1
                    user.save(flush: true)
                    return success()
                }
            }
            on ("passwordMismatch").to "changePassword"
            on ("invalidLogin").to "changePassword"
            on ("success").to "finish"
        }

        finish {

        }
    }

    def createUserFlow = {
        input {
            sub(false)
            isRegistration(false)
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

                if (User.findByEmailAddress(params.emailAddress)) {
                    flash.message = "A user with that email address already exists!"
                    return error()
                }

                flow.userData["username"] = params.emailAddress
                flow.userData["password"] = params.password1
                flow.userData["emailAddress"] = params.emailAddress
                flow.userData["firstName"] = params.firstName
                flow.userData["lastName"] = params.lastName

                if (flow.isRegistration) {
                    skipAddress()
                }
            }
            on ("success").to "enterShipping"
            on ("skipAddress").to "confirm"
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
                redirect(uri: "/")
            }
            on ("success").to "end"
        }

        end() {

        }

        success () {

        }
    }
}
