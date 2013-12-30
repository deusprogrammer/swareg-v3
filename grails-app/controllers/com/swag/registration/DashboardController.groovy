package com.swag.registration
import com.swag.registration.domain.*
import grails.converters.JSON
import grails.plugins.springsecurity.SpringSecurityService

class DashboardController {
	SpringSecurityService springSecurityService
	
    def index() {
		if (!springSecurityService.loggedIn) {
			return
		}
		
		List badges = Registration.list().findAll {
			Registration it ->
				it.user.emailAddress == springSecurityService.currentUser.emailAddress
		}.sort {
			Registration it ->
				//it.id
				it.registrationLevel.name
		}
		List events = Event.list().findAll {
			Event it ->
				it.user.emailAddress == springSecurityService.currentUser.emailAddress
		}.sort {
			Event it ->
				it.name
		}
		List gifted = Gift.getGiven(springSecurityService.currentUser).sort {
			Gift it ->
				it.badge.registrationLevel.name
		}
		List received = Gift.getReceived(springSecurityService.currentUser).sort {
			Gift it ->
				it.badge.registrationLevel.name
		}
		
		print "OWNED:    ${badges}"
		print "GIFTED:   ${gifted*.badge}"
		print "RECEIVED: ${received*.badge}"
		
		return [badges: badges - gifted*.badge, gifted: gifted*.badge, received: received*.badge, events: events]
	}
	
	def gift(Long id) {
		String to = params.to
		
		print "BADGE: ${id}"
		print "EMAIL: ${to}"
		
		Registration badge = Registration.get(id)
		if (!badge || badge.user != springSecurityService.currentUser) {
			log.error("Cannot find badge!")
			render([success: false] as JSON)
		}
		
		Gift gift = Gift.create(to, badge)
		
		if (!gift) {
			log.error("Cannot find gifted badge!")
			render([success: false] as JSON)
		}
		
		render([success: true] as JSON)
	}
	
	def ungift(Long id) {
		print "BADGE: ${id}"
		
		Registration badge = Registration.get(id)
		if (!badge || badge.user != springSecurityService.currentUser) {
			log.error("Cannot find badge!")
			render([success: false] as JSON)
		}
		
		Gift gift = Gift.findByBadge(badge)
		
		if (!gift) {
			log.error("Cannot find gifted badge!")
			render([success: false] as JSON)
		}
		
		gift.delete()
		
		render([success: true] as JSON)
	}
	
	def accept(Long id) {
		print "BADGE: ${id}"
		
		Registration badge = Registration.get(id)
		if (!badge) {
			log.error("Cannot find badge!")
			render([success: false] as JSON)
		}
		
		Gift gift = Gift.findByBadge(badge)
		
		if (!gift || gift.receiver != springSecurityService.currentUser) {
			log.error("Cannot find gifted badge!")
			render([success: false] as JSON)
		}
		
		gift.completeTransfer()
		gift.delete()
		
		render([success: true] as JSON)
	}
	
	def viewBadge(Long id) {
		Registration badge = Registration.get(id)
		
		[badge: badge]
	}
	
	def viewEvent(Long id) {
		Event event = Event.get(id)
		
		[event: event]
	}
}