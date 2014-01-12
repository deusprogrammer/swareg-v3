package com.swag.registration.domain

import org.springframework.dao.DataIntegrityViolationException
import com.swag.registration.security.acl.EventService
import grails.converters.JSON

class EventController {

    EventSchedulerService eventSchedulerService
    EventService eventService
	
	// Return a css file with custom css defined
	def css(Long id) {
		render "Not implemented"
	}

    def index() {
        redirect(action: "list")
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        List<Event> events = eventService.list(params)
        int count = 0
        events.each {
            count++
        }

        [eventInstanceList: events, eventInstanceTotal: count]
    }

    def create() {
        [eventInstance: new Event(params)]
    }

    def save() {
        Map map = JSON.parse(params.eventData)
        def eventInstance = eventService.create(map)

        if (!eventInstance.save(flush: true)) {
            render(view: "create", model: [eventInstance: eventInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])
        redirect(action: "show", id: eventInstance.id)
    }

    def show(Long id) {
        def eventInstance = eventService.get(id)
        if (!eventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), id])
            redirect(action: "list")
            return
        }

        [eventInstance: eventInstance]
    }

    def edit(Long id) {
        def eventInstance = eventService.get(id)
        if (!eventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), id])
            redirect(action: "list")
            return
        }

        [eventInstance: eventInstance]
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

        //eventInstance.properties = params
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

    def clone(Long id) {
        if (!params.year && !params.numeral) {
            flash.message = "You must provide year or numeral for new con!"
            redirect(action: "list")
            return
        }

        def scheduleRet = eventSchedulerService.cloneEvent(id, params.year, params.numeral)

        if (scheduleRet["success"]) {
            flash.message = "Event cloned successfully!"
            redirect(action: "show", id: scheduleRet["event"])
            return
        }
        else {
            flash.message = "Event clone failed!"
            redirect(action: "show", id: id)
            return
        }
    }
}
