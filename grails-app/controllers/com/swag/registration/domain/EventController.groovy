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
			response.setStatus(404)
			return
		}
		
		[event: event, positions: event.positions]
	}
	
	def showOpenPositions(String id) {
		Event event = Event.findByUuid(id)
		
		println "UUID: ${id}"
		
		if (!event) {
			response.setStatus(404)
			return
		}
		
		ArrayList<StaffPosition> positions = event.positions.findAll { 
			StaffPosition position -> position.available 
		}
		
		render(view: "showPositions", model: [event: event, positions: positions])
	}
}
