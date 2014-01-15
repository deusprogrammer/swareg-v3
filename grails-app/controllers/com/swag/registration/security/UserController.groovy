package com.swag.registration.security

import org.springframework.dao.DataIntegrityViolationException

import com.swag.registration.domain.Registration
import com.swag.registration.security.acl.UserService

import grails.converters.JSON

class UserController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    UserService userService

    def index() {
        redirect(action: "list", params: params)
        return
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def show(Long id) {
        def userInstance = User.get(id)

        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def edit(Long id) {
        def userInstance = User.get(id)

        userService.accessCheck(userInstance)

        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def completeRegistration(String id) {
        Registration registration = Registration.findByUuid(id)

        if (!registration) {
            println "Couldn't find a registration with that uuid!"
            redirect(controller: "error", action: "notFound")
            return
        }

        if (registration.user.registrationComplete) {
            println "User already exists!"
            redirect(controller: "error", action: "notFound")
            return
        }

        session["userId"] = registration.user.id

        render(view: "edit", model: [userInstance: registration.user])
    }

    def update(Long id, Long version) {
        def userInstance = User.get(id)

        userService.accessCheck(userInstance)

        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        userInstance.registrationComplete = true

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
        return
    }

    def delete(Long id) {
        def userInstance = User.get(id)

        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
            return
        }
    }

    def autoCompleteJSON = {
        println "RECEIVED " + params.term
        def users = User.findByUsernameLike(params.term + "%")

        def usernames = users.collect {it.username}

        render usernames as JSON
    }
}
