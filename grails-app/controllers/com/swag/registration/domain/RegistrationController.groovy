package com.swag.registration.domain

import com.swag.registration.security.acl.EventService
import org.springframework.dao.DataIntegrityViolationException

class RegistrationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	EventService eventService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        []
    }

    def create() {
    }

    /*
    def save() {
        def emailAddress = params["emailAddress"]
        def eventId = params["eventId"]
        def level = params["registrationLevel.id"]

        //Create new user from emailAddress
        def user = User.findByEmailAddress(emailAddress)

        if (!user) {
            user = new User()
            user.emailAddress = emailAddress
            user.password = UUID.randomUUID().toString()
            user.registrationComplete = false
            user.save()
        }

        def event = Event.get(eventId)

        //Create registration
        def role = new Role(event: event, role: UserRole.ATENDEE)
        def regLevel = RegistrationLevel.get(level)

        def registrationInstance = new Registration()
        registrationInstance.user = user
        registrationInstance.event = event
        registrationInstance.registrationLevel = regLevel
        registrationInstance.paid = true
        registrationInstance.receiptNumber = UUID.randomUUID()
        if (!registrationInstance.save(flush: true)) {
            println "Can't save registration!"
            registrationInstance.errors.each {println it}
            flash.message = "Can't save registration!"
            redirect(action: "manualRegistration", id: eventId)
            return
        }
        else {
            user.addToRoles(role)
            role.save()
            user.save()
        }

        if (!user.registrationComplete) {
            //Send confirmation email
            try {
               sendMail {
                   to user.emailAddress
                   subject "${event.toString()} Registration Confirmation"
                   html "<p>Thank you for your registration for ${event.toString()}!  Please go below to complete your registration!</p><p><a href='${createLink(controller: "user", action: "completeRegistration", id: registrationInstance.uuid, absolute: true)}'>${createLink(controller: "user", action: "completeRegistration", id: registrationInstance.uuid, absolute: true)}</a></p>"
            }
            } catch (Exception e) {
                println "Unable to send confirmation email!"
            }
        }

        if (!params.eventId) {
            redirect(controller: "error", action: "general")
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'registration.label', default: 'Registration'), registrationInstance.id])
        redirect(action: "show", id: registrationInstance.id)
    }
    */

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

    def edit(Long id) {
        def registrationInstance = Registration.get(id)
		
        if (!registrationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'registration.label', default: 'Registration'), id])
            redirect(action: "list")
            return
        }
		
		eventService.checkWrite(registrationInstance)

        def globalEvent = Event.get(1)
        def levels = globalEvent.levels + registrationInstance.event.levels

        [registrationInstance: registrationInstance, levels: levels]
    }

    def update(Long id, Long version) {
        def registrationInstance = Registration.get(id)
		
        if (!registrationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'registration.label', default: 'Registration'), id])
            redirect(action: "list")
            return
        }
		
		eventService.checkWrite(registrationInstance)

        if (version != null) {
            if (registrationInstance.version > version) {
                registrationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'registration.label', default: 'Registration')] as Object[],
                          "Another user has updated this Registration while you were editing")
                render(view: "edit", model: [registrationInstance: registrationInstance])
                return
            }
        }

        registrationInstance.properties = params

        if (!registrationInstance.save(flush: true)) {
            render(view: "edit", model: [registrationInstance: registrationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'registration.label', default: 'Registration'), registrationInstance.id])
        redirect(action: "show", id: registrationInstance.id)
    }

    def delete(Long id) {
        def registrationInstance = Registration.get(id)
		
        if (!registrationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'registration.label', default: 'Registration'), id])
            redirect(action: "list")
            return
        }
		
		eventService.checkDelete(registrationInstance)

        try {
            registrationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'registration.label', default: 'Registration'), id])
            redirect(action: "listByEvent", id: registrationInstance.event.id)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'registration.label', default: 'Registration'), id])
            redirect(action: "show", id: id)
        }
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
