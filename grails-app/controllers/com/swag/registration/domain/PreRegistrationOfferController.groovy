package com.swag.registration.domain

import java.text.SimpleDateFormat
import org.springframework.dao.DataIntegrityViolationException

import com.swag.registration.security.acl.EventService

class PreRegistrationOfferController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	EventService eventService
	
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy")

    def create() {
        println "IN CREATE()"
        [preRegistrationOfferInstance: new PreRegistrationOffer(params), levelId: params.levelId, eventId: params.eventId]
    }

    def save() {
		params.startDate = formatter.parse(params.startDate)
		params.endDate   = formatter.parse(params.endDate)
		
		println "PARAMS: " + params
		
		def preRegistrationOfferInstance = new PreRegistrationOffer(params)
		eventService.checkWrite(preRegistrationOfferInstance)
		
        if (!preRegistrationOfferInstance.save(flush: true)) {
            render(view: "create", model: [preRegistrationOfferInstance: preRegistrationOfferInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'preRegistrationOffer.label', default: 'PreRegistrationOffer'), preRegistrationOfferInstance.id])
        redirect(controller: "registrationLevel", action: "show", id: params.registrationLevel.id)
    }

    def show(Long id) {
        def preRegistrationOfferInstance = PreRegistrationOffer.get(id)
		
        if (!preRegistrationOfferInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'preRegistrationOffer.label', default: 'PreRegistrationOffer'), id])
            redirect(action: "list")
            return
        }
		
		eventService.checkRead(preRegistrationOfferInstance)

        [preRegistrationOfferInstance: preRegistrationOfferInstance, eventId: preRegistrationOfferInstance.registrationLevel.event.id]
    }

    def edit(Long id) {
        def preRegistrationOfferInstance = PreRegistrationOffer.get(id)
		
        if (!preRegistrationOfferInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'preRegistrationOffer.label', default: 'PreRegistrationOffer'), id])
            redirect(action: "list")
            return
        }
		
		eventService.checkWrite(preRegistrationOfferInstance)

        [preRegistrationOfferInstance: preRegistrationOfferInstance, levelId: preRegistrationOfferInstance.registrationLevel.id, eventId: preRegistrationOfferInstance.registrationLevel.event.id]
    }

    def update(Long id, Long version) {
        def preRegistrationOfferInstance = PreRegistrationOffer.get(id)
		params.startDate = formatter.parse(params.startDate)
		params.endDate   = formatter.parse(params.endDate)
		
        if (!preRegistrationOfferInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'preRegistrationOffer.label', default: 'PreRegistrationOffer'), id])
            redirect(controller: "registrationLevel", action: "show", id: params.registrationLevel.id)
            return
        }
		
		eventService.checkWrite(preRegistrationOfferInstance)

        if (version != null) {
            if (preRegistrationOfferInstance.version > version) {
                preRegistrationOfferInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'preRegistrationOffer.label', default: 'PreRegistrationOffer')] as Object[],
                          "Another user has updated this PreRegistrationOffer while you were editing")
                render(view: "edit", model: [preRegistrationOfferInstance: preRegistrationOfferInstance])
                return
            }
        }

        preRegistrationOfferInstance.properties = params

        if (!preRegistrationOfferInstance.save(flush: true)) {
            render(view: "edit", model: [preRegistrationOfferInstance: preRegistrationOfferInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'preRegistrationOffer.label', default: 'PreRegistrationOffer'), preRegistrationOfferInstance.id])
        redirect(controller: "registrationLevel", action: "show", id: params.registrationLevel.id)
    }

    def delete(Long id) {
        def preRegistrationOfferInstance = PreRegistrationOffer.get(id)
		
        if (!preRegistrationOfferInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'preRegistrationOffer.label', default: 'PreRegistrationOffer'), id])
            redirect(controller: "registrationLevel", action: "show", id: levelId)
            return
        }
		
		eventService.checkDelete(preRegistrationOfferInstance)

        try {
            preRegistrationOfferInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'preRegistrationOffer.label', default: 'PreRegistrationOffer'), id])
            redirect(controller: "registrationLevel", action: "show", id: levelId)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'preRegistrationOffer.label', default: 'PreRegistrationOffer'), id])
            redirect(controller: "registrationLevel", action: "show", id: levelId)
        }
    }
}
