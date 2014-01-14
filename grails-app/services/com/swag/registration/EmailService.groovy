package com.swag.registration

import com.swag.registration.domain.Event
import com.swag.registration.domain.Gift
import com.swag.registration.domain.PasswordReset
import com.swag.registration.domain.order.PayPalOrder
import com.swag.registration.security.Activation
import com.swag.registration.security.User

class EmailService {
	def g = new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()
	def grailsApplication
	
	def isSetup() {
		return grailsApplication.config.grails.mail.username != null && grailsApplication.config.grails.mail.password != null
	}
	
	def sendPasswordResetEmail(PasswordReset pr) {
		try {
			sendMail {
				to pr.user.emailAddress
				subject "[SWAreG] Password Reset"
				html(
					view: "/emails/passwordReset",
					model: [url: g.createLink(controller: 'userFlow', action: 'setPassword', id: pr.token, absolute: true)]
				)
			}
		 } catch (Exception e) {
			 println e.message
			 println "Failed to send email."
		 }
	}

    def sendOrderEmail(PayPalOrder ppo) {
		try {
			sendMail {
				to ppo.user.emailAddress
				subject "[SWAreG] Registration Confirmation"
				html( 
					view: "/emails/order", 
					model: [order: ppo]
				)
			}
		 } catch (Exception e) {
		 	println e.message
		 	println "Failed to send email."
		 }
    }
	
	def sendUserCreateEmail(User user) {
		try {
			sendMail {
				to user.emailAddress
				subject "[SWAreG] User Creation Confirmation"
				html( 
					view: "/emails/user", 
					model: [user: user]
				)
			}
		 } catch (Exception e) {
 		 	println e.message
		 	println "Failed to send email."
		 }
	}
	
	def sendEventCreateEmail(Event event) {
		try {
			sendMail {
				to event.user.emailAddress
				subject "[SWAreG] ${event.toString()} Creation Confirmation"
				html( 
					view: "/emails/event", 
					model: [event: event]
				)
			}
		 } catch (Exception e) {
		 	println e.message
		 	println "Failed to send email."
		 }
	}
	
	def sendGiftEmail(Gift gift) {
		try {
			if (gift.receiver.enabled) {
				sendMail {
					to gift.receiver.emailAddress
					subject "[SWAreG] You Received a gift badge!  ${gift.badge.event}"
					html(
						view: "/emails/registeredGift",
						model: [gift: gift]
					)
				}
			} else {
				sendMail {
					to gift.receiver.emailAddress
					subject "[SWAreG] You Received a gift badge!  ${gift.badge.event}"
					html(
						view: "/emails/unregisteredGift",
						model: [gift: gift]
					)
				}
			}
		 } catch (Exception e) {
 		 	println e.message
		 	println "Failed to send email."
		 }
	}
	
	def sendOnsiteOrderEmail(Activation activation) {
		try {
			sendMail {
				to activation.user.emailAddress
				subject "[SWAreG] Onsite Registration Confirmation"
				html(
					view: "/emails/unregisteredOrder",
					model: [activation: activation]
				)
			}
		 } catch (Exception e) {
			 println e.message
			 println "Failed to send email."
		 }
	}
}
