package com.swag.registration.domain

import org.springframework.dao.DataIntegrityViolationException
import com.swag.registration.security.acl.EventService
import grails.converters.JSON
import grails.plugins.springsecurity.SpringSecurityService

class EventController {
    EventSchedulerService eventSchedulerService
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

    def update(Long id, Long version) {
        def eventInstance = eventService.get(id)
        if (!eventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (eventInstance.version > version) {
                eventInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'event.label', default: 'Event')] as Object[],
                          "Another user has updated this Event while you were editing")
                render(view: "edit", model: [eventInstance: eventInstance])
                return
            }
        }

        EventService.update(eventInstance, params)

        if (!eventInstance.save(flush: true)) {
            render(view: "edit", model: [eventInstance: eventInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])
        redirect(action: "show", id: eventInstance.id)
    }

    def delete(Long id) {
        def eventInstance = eventService.get(id)
        if (!eventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), id])
            redirect(action: "list")
            return
        }

        try {
            EventService.delete(eventInstance)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'event.label', default: 'Event'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'event.label', default: 'Event'), id])
            redirect(action: "show", id: id)
        }
    }
}
