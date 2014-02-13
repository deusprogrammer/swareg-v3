package com.swag.registration.domain

import org.springframework.dao.DataIntegrityViolationException
import com.swag.registration.security.acl.EventService
import grails.converters.JSON
import grails.plugins.springsecurity.SpringSecurityService

class EventController {
    EventService eventService
	
	// Return a css file with custom css defined
	def css(Long id) {
		render "Not implemented"
	}
	
	def showPositions(Long id) {
		Event event = Event.findByUuid(id)
		
		if (!event) {
			response.sendError(404)
			return
		}
		
		[event: event, positions: event.positions]
	}
	
	def showOpenPositions(String id) {
		Event event = Event.findByUuid(id)
		
		println "UUID: ${id}"
		
		if (!event) {
			response.sendError(404)
			return
		}
		
		ArrayList<StaffPosition> positions = event.positions.findAll { 
			StaffPosition position -> position.available 
		}
		
		render(view: "showPositions", model: [event: event, positions: positions])
	}
	
	def edit(Long id) {
		Event event = Event.get(id)
		
		if (!event) {
			response.sendError(404)
			return
		}
		
		eventService.checkAdmin(event)
		
		[event: event]
	}
	
	def update(Long id) {
		Event event = Event.get(id)
		
		if (!event) {
			response.setStatus(403)
			return
		}
		
		eventService.checkAdmin(event)
		
		event.properties = params
		
		if (!event.save(flush: true)) {
			flash.message = "Unable to update event."
		}
		
		redirect(action: "edit")
	}
}
