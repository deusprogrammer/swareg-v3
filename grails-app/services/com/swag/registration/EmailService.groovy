package com.swag.registration

import com.swag.registration.domain.Event
import com.swag.registration.domain.order.PayPalOrder
import com.swag.registration.security.User

class EmailService {

    def sendOrderEmail(PayPalOrder ppo) {
		try {
			sendMail {
				to ppo.user.emailAddress
				subject "Registration Confirmation"
				html ""
			}
		 } catch (Exception e) {
		 	println "Failed to send email."
		 }
    }
	
	def sendUserCreateEmail(User user) {
		try {
			sendMail {
				to user.emailAddress
				subject "User Creation Confirmation"
				html ""
			}
		 } catch (Exception e) {
		 	println "Failed to send email."
		 }
	}
	
	def sendEventCreateEmail(Event event) {
		try {
			sendMail {
				to event.user.emailAddress
				subject "${event.toString()} Creation Confirmation"
				html ""
			}
		 } catch (Exception e) {
		 	println "Failed to send email."
		 }
	}
}
