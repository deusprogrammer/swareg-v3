package com.swag.registration.domain

import com.swag.registration.security.acl.EventService
import org.springframework.dao.DataIntegrityViolationException

class RegistrationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    EventService eventService

    def show(Long id) {
        def registrationInstance = Registration.get(id)

        if (!registrationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'registration.label', default: 'Registration'), id])
            redirect(action: "list")
            return
        }

        eventService.checkRead(registrationInstance)

        [registrationInstance: registrationInstance]
    }

    def manualRegistration(Long id) {
        if (!id || id == 1) {
            println "EventId must be entered!"
            redirect(controller: "error", action: "general")
            return
        }

        //Gather registration levels
        def event = eventService.get(id)

        if (!event) {
            println "Event ${id} can't be found"
            redirect(controller: "error", action: "notFound")
            return
        }

        def levels = []

        /*
        if (sessionService.isAtleastAdmin(id)) {
            levels = globalEvent.levels + event.levels
        }
        else {
            levels = globalEvent.levels + event.levels.findAll(!it.needAdmin)
        }
        */

        levels.sort {it.currentPrice}

        render(view: "create", model: [registrationInstance: new Registration(params), eventName: event.toString(), eventId: params.id, levels: levels])
    }
}
