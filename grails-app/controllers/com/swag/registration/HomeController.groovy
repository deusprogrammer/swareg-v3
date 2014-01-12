package com.swag.registration
import com.swag.registration.domain.*
import com.swag.registration.domain.order.PayPalOrder
import com.swag.registration.security.User

class HomeController {
	def grailsApplication
	EmailService emailService

    def index() {
    }
	
	def testEventEmail(Long id) {
		Event event = Event.get(id)
		if (!event) {
			render "Nope"
			return
		}
		
		emailService.sendEventCreateEmail(event)
		render "Email sent"
	}
	
	def testUserEmail(Long id) {
		User user = User.get(id)
		if (!user) {
			render "Nope"
			return
		}
		
		emailService.sendUserCreateEmail(user)
		render "Email sent"
	}
	
	def testOrderEmail(Long id) {
		PayPalOrder order = PayPalOrder.get(id)
		if (!order) {
			render "Nope"
			return
		}
		
		emailService.sendOrderEmail(order)
		render "Email sent"
	}
	
	def testGiftEmail(Long id) {
		Gift gift = Gift.get(id)
		if (!gift) {
			render "Nope"
			return
		}
		
		emailService.sendGiftEmail(gift)
		render "Email sent"
	}
}
