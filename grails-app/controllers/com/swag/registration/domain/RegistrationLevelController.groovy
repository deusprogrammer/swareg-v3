package com.swag.registration.domain

import org.springframework.dao.DataIntegrityViolationException
import com.swag.registration.security.acl.EventService

class RegistrationLevelController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	EventService eventService

    def create() {
        def loggedInUser = session["userId"]

        if (!loggedInUser) {
            flash.message = "No user logged in!"
            redirect(controller: "user", action: "create")
            return
        }

        def user = User.get(loggedInUser)

        [registrationLevelInstance: new RegistrationLevel(params), eventId: params.eventId]
    }

    def save() {
        def registrationLevelInstance = new RegistrationLevel(params)
		
		eventService.checkWrite(registrationLevelInstance)
		
        if (!registrationLevelInstance.save(flush: true)) {
            render(view: "create", model: [registrationLevelInstance: registrationLevelInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'registrationLevel.label', default: 'RegistrationLevel'), registrationLevelInstance.id])
        redirect(controller: "event", action: "show", id: registrationLevelInstance.event.id)
    }

    def show(Long id) {
        def registrationLevelInstance = RegistrationLevel.get(id)
		
        if (!registrationLevelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'registrationLevel.label', default: 'RegistrationLevel'), id])
            redirect(action: "list")
            return
        }
		
		eventService.checkRead(registrationLevelInstance)

        [registrationLevelInstance: registrationLevelInstance]
    }

    def edit(Long id) {
        def registrationLevelInstance = RegistrationLevel.get(id)
		
        if (!registrationLevelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'registrationLevel.label', default: 'RegistrationLevel'), id])
            redirect(action: "list")
            return
        }
		
		eventService.checkWrite(registrationLevelInstance)

        def loggedInUser = session["userId"]

        if (!loggedInUser) {
            flash.message = "No user logged in!"
            redirect(controller: "user", action: "create")
            return
        }

        def user = User.get(loggedInUser)

        def eventId = registrationLevelInstance.event.id

        [registrationLevelInstance: registrationLevelInstance, events: user.events, eventId: eventId]
    }

    def update(Long id, Long version) {
        def registrationLevelInstance = RegistrationLevel.get(id)
		
        if (!registrationLevelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'registrationLevel.label', default: 'RegistrationLevel'), id])
            redirect(action: "list")
            return
        }
		
		eventService.checkWrite(registrationLevelInstance)

        if (version != null) {
            if (registrationLevelInstance.version > version) {
                registrationLevelInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'registrationLevel.label', default: 'RegistrationLevel')] as Object[],
                          "Another user has updated this RegistrationLevel while you were editing")
                render(view: "edit", model: [registrationLevelInstance: registrationLevelInstance])
                return
            }
        }

        registrationLevelInstance.properties = params

        if (!registrationLevelInstance.save(flush: true)) {
            render(view: "edit", model: [registrationLevelInstance: registrationLevelInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'registrationLevel.label', default: 'RegistrationLevel'), registrationLevelInstance.id])
        redirect(controller: "event", action: "show", id: registrationLevelInstance.event.id)
    }

    def delete(Long id) {
        def registrationLevelInstance = RegistrationLevel.get(id)
		
        def eventId = registrationLevelInstance.event.id
        if (!registrationLevelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'registrationLevel.label', default: 'RegistrationLevel'), id])
            redirect(controller: "event", action: "show", id: eventId)
            return
        }
		
		eventService.checkDelete(registrationLevelInstance)

        try {
            registrationLevelInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'registrationLevel.label', default: 'RegistrationLevel'), id])
            redirect(controller: "event", action: "show", id: eventId)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'registrationLevel.label', default: 'RegistrationLevel'), id])
            redirect(controller: "event", action: "show", id: eventId)
        }
    }
}
