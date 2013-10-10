package com.swag.registration.domain

import org.springframework.dao.DataIntegrityViolationException

class ConfigHolderController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [configHolderInstanceList: ConfigHolder.list(params), configHolderInstanceTotal: ConfigHolder.count()]
    }

    def create() {
        [configHolderInstance: new ConfigHolder(params)]
    }

    def save() {
        def configHolderInstance = new ConfigHolder(params)
        if (!configHolderInstance.save(flush: true)) {
            render(view: "create", model: [configHolderInstance: configHolderInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'configHolder.label', default: 'ConfigHolder'), configHolderInstance.id])
        redirect(action: "show", id: configHolderInstance.id)
    }

    def show(Long id) {
        def configHolderInstance = ConfigHolder.get(id)
        if (!configHolderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'configHolder.label', default: 'ConfigHolder'), id])
            redirect(action: "list")
            return
        }

        [configHolderInstance: configHolderInstance]
    }

    def edit(Long id) {
        def configHolderInstance = ConfigHolder.get(id)
        if (!configHolderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'configHolder.label', default: 'ConfigHolder'), id])
            redirect(action: "list")
            return
        }

        [configHolderInstance: configHolderInstance]
    }

    def update(Long id, Long version) {
        def configHolderInstance = ConfigHolder.get(id)
        if (!configHolderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'configHolder.label', default: 'ConfigHolder'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (configHolderInstance.version > version) {
                configHolderInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'configHolder.label', default: 'ConfigHolder')] as Object[],
                          "Another user has updated this ConfigHolder while you were editing")
                render(view: "edit", model: [configHolderInstance: configHolderInstance])
                return
            }
        }

        configHolderInstance.properties = params

        if (!configHolderInstance.save(flush: true)) {
            render(view: "edit", model: [configHolderInstance: configHolderInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'configHolder.label', default: 'ConfigHolder'), configHolderInstance.id])
        redirect(action: "show", id: configHolderInstance.id)
    }

    def delete(Long id) {
        def configHolderInstance = ConfigHolder.get(id)
        if (!configHolderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'configHolder.label', default: 'ConfigHolder'), id])
            redirect(action: "list")
            return
        }

        try {
            configHolderInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'configHolder.label', default: 'ConfigHolder'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'configHolder.label', default: 'ConfigHolder'), id])
            redirect(action: "show", id: id)
        }
    }
}
