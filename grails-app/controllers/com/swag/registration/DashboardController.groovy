package com.swag.registration
import java.text.SimpleDateFormat;

import com.swag.registration.domain.*
import com.swag.registration.security.acl.*
import grails.converters.JSON
import grails.plugins.springsecurity.SpringSecurityService
import java.text.SimpleDateFormat

class DashboardController {
	SpringSecurityService springSecurityService
	EventService eventService
	
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy")
	
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
		print "GIFTED:   ${gifted}"
		print "RECEIVED: ${received}"
		
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
		
		if (!badge || badge.user != springSecurityService.currentUser) {
			response.setStatus(404)
			return
		}
		
		[badge: badge]
	}
	
	def viewEvent(Long id) {
		Event event = Event.get(id)
		
		if (!event || event.user != springSecurityService.currentUser) {
			response.setStatus(404)
			return
		}
		
		event.levels.each { RegistrationLevel it ->
			println "LEVEL: ${it}"
			it.preRegOffers.each { PreRegistrationOffer offer ->
				println "\tOFFER: ${offer}"
			}
		}
		
		[event: event]
	}
	
	def eventMenu(Long id) {
		[eventId: id]
	}
	
	def viewRegistrations(Long id) {
		Event event = eventService.get(id)
		
		if (!event || event.user != springSecurityService.currentUser) {
			response.setStatus(404)
			return
		}
		
		println "EVENT: ${event}"
		
		[event: event]
	}
	
	def tierDash(Long id) {
		Event event = Event.get(id)
		
		if (!event || event.user != springSecurityService.currentUser) {
			response.setStatus(404)
			return
		}
		
		[event: event]
	}
	
	def saveTier() {
		Event event = Event.get(params.eventId)
		
		if (!event || event.user != springSecurityService.currentUser) {
			response.setStatus(404)
			return
		}
		
		RegistrationLevel rl = new RegistrationLevel(params)
		rl.event = event
		
		if (!rl.save()) {
			flash.message = "Unable to save registration level!"
		} else {
			flash.message = "Registration level created successfully!"
		}
		
		redirect(action: "tierDash", id: params.eventId)
	}
	
	def deleteTier(Long id) {
	   RegistrationLevel rl = RegistrationLevel.get(id)

        if (!rl || rl.event.user != springSecurityService.currentUser) {
        	println "Unable to delete tier"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rl.label', default: 'RegistrationLevel'), id])
            redirect(action: "tierDash", id: rl.event.id)
            return
        }

        eventService.checkDelete(rl)

        try {
            rl.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'rl.label', default: 'RegistrationLevel'), id])
            redirect(action: "tierDash", id: rl.event.id)
            return
        }
        catch (Exception e) {
        	println "Unable to delete tier!  ${e.message}"
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'rl.label', default: 'RegistrationLevel'), id])
            redirect(action: "tierDash", id: rl.event.id)
            return
        }
	}
	
	def preRegOfferDash(Long id) {
		Event event = Event.get(id)
		
		if (!event || event.user != springSecurityService.currentUser) {
			response.setStatus(404)
			return
		}
		
		[event: event]
	}
	
	def savePreRegOffer() {
		RegistrationLevel level = RegistrationLevel.get(params.tier)
		
		if (!level || level.event.user != springSecurityService.currentUser) {
			response.setStatus(404)
			return
		}
		
		eventService.checkWrite(level)
		
		params.startDate = formatter.parse(params.startDate)
		params.endDate   = formatter.parse(params.endDate)
		
		PreRegistrationOffer offer = new PreRegistrationOffer(params)
		offer.registrationLevel = level
		if (!offer.save(flush: true)) {
			print "ERRORS SAVING PRE-REG OFFER!  ${offer.errors}"
		}
				
		redirect(action: "preRegOfferDash", id: level.event.id)
		return
	}
	
	def deletePreRegOffer(Long id) {
	    PreRegistrationOffer offer = PreRegistrationOffer.get(id)

        if (!offer) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'offer.label', default: 'PreRegistrationOffer'), id])
            redirect(action: "preRegOfferDash", id: offer.registrationLevel.event.id)
            return
        }

        eventService.checkDelete(offer)

        try {
            offer.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'offer.label', default: 'PreRegistrationOffer'), id])
            redirect(action: "preRegOfferDash", id: offer.registrationLevel.event.id)
            return
        }
        catch (Exception e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'offer.label', default: 'PreRegistrationOffer'), id])
            redirect(action: "preRegOfferDash", id: offer.registrationLevel.event.id)
            return
        }
	}
}