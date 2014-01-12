package com.swag.registration

import com.swag.registration.domain.Event
import com.swag.registration.domain.Gift
import com.swag.registration.domain.order.PayPalOrder
import com.swag.registration.security.User

class EmailService {
	def grailsApplication

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
		println "TO:              ${event.user.emailAddress}"
		println "FROM:            ${grailsApplication.config.grails.mail.username}"
		println "PASSWORD:        ${grailsApplication.config.grails.mail.password}"
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
}
