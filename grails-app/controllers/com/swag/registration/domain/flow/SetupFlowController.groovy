package com.swag.registration.domain.flow

import java.awt.FlowLayout

import com.sun.org.apache.xerces.internal.impl.xs.traversers.OneAttr
import com.swag.registration.SetupUpdaterService
import com.swag.registration.domain.ConfigHolder
import com.swag.registration.security.User
import grails.plugins.springsecurity.SpringSecurityService
import grails.plugins.springsecurity.Secured

class SetupFlowController {
	SetupUpdaterService setupUpdaterService
    SpringSecurityService springSecurityService
	def grailsApplication

    @Secured(['ROLE_GLOBAL'])
    def setupFlow = {
        start {
            action {
                if (!ConfigHolder.getSwitch("swareg.setup")) {
                    firstTime()
                }
            }
            on("firstTime").to "welcome"
            on("success").to "setupPayPalApi"
        }

        welcome {
            on ("ok").to "setupPayPalApi"
        }

        setupPayPalApi {
            on("next") {
                ConfigHolder.setConfig("payPal.clientId", params.clientId)
                ConfigHolder.setConfig("payPal.secret", params.secret)
            }.to "setupEmail"
        }
		
		setupEmail {
			on("next") {
				ConfigHolder.setConfig("email.address", params.emailAddress)
				ConfigHolder.setConfig("email.password", params.emailPassword)
				setupUpdaterService.updateEmail(params.emailAddress, params.emailPassword)
			}.to "changePassword"
		}

        changePassword {
            on("set") {
                if (params.password == params.confirmPassword) {
                    User user = User.findByUsername("global_admin")
                    user.password = params.password
                    user.save()
                    ConfigHolder.setSwitch("swareg.setup", true)
                } else {
                    flash.message = "Your passwords don't match"
                    error()
                }
            }.to "finish"
            on("error").to "changePassword"
        }

        finish()
    }
}
