package com.swag.registration.domain

import grails.converters.JSON
import java.util.UUID
import com.swag.registration.security.*
import org.apache.commons.lang.RandomStringUtils

class JSController {

    def JSSessionService
    def sessionService
    def registrationService
    def cryptoService

    //********************************************************

    def getRegistrationLevels(String id) {
        println "getRegistrationLevelsJs() hit!"

        def eventId = id
        def ret = [status: "success", message: "", levels: []]

        if (!eventId) {
            ret["status"] = "failure"
            ret["message"] = "Invalid parameters passed."
        } else {
            //Gather registration levels
            def globalEvent = Event.get(1)
            def event = Event.findByUuid(eventId)

            if (!event) {
                ret["status"] = "failure"
                ret["message"] = "Cannot find event with id ${eventId}}"
            } else {
                def levels = event.levels.findAll{!it.needAdmin}
                event.levels.each {println it.name + "(Admin needed: ${it.needAdmin ? 'yes' : 'no'})"}
                levels.each {ret["levels"] += [id: it.id, name: it.name, text: it.description, price: it.currentPrice]; println it}
            }
        }

        withFormat {
            json {render ret as JSON}
            js   {render "${params.callback}(${ret as JSON})"}
        }
    }

    def getEventName(String id) {
        def event = Event.findByUuid(id)
        def ret = [status: "success"]

        if (event) {
            ret["name"] = event.name
        }
        else {
            ret["status"] = "failure"
            ret["message"] = "Can't find event with that id"
        }

        withFormat {
            json {render ret as JSON}
            js   {render "${params.callback}(${ret as JSON})"}
        }
    }

    def registerForEventOnsite(String id) {
        println "registerForEventOnsite() hit!"

        def jsonData = request.JSON
        def eventId = id
        def level = jsonData.level
        def emailAddress = jsonData.email
        def receiptNumber = jsonData.receiptNumber
        def hash = jsonData.hash

        def event = Event.findByUuid(eventId)

        //Check the hashes to ensure the user is who they say they are.
        def combined = (eventId + emailAddress + level + receiptNumber)
        def hmac = cryptoService.hmacMD5(event.apiKey.getBytes(), combined.getBytes())


        println "LEVEL: ${level}"
        println "EMAIL: ${emailAddress}"
        println "HMAC: ${hmac}"
        println "HASH: ${hash}"

        if (hash != hmac) {
            def ret = [status: "failure", message: "Unable to authenticate!"]

            withFormat {
                json {render ret as JSON}
                js   {render "${params.callback}(${ret as JSON})"}
            }
            return
        }

        def ret = [:]

        //Create new user from emailAddress
        def user = User.findByEmailAddress(emailAddress)

        if (!user) {
            user = new User()
            user.username = emailAddress
            user.emailAddress = emailAddress
            user.password = RandomStringUtils.random(16)
            user.registrationComplete = false
            user.save()
        }

        //Create registration
        def regLevel = RegistrationLevel.get(level)

        def registrationInstance = new Registration()
        registrationInstance.user = user
        registrationInstance.event = event
        registrationInstance.registrationLevel = regLevel
        registrationInstance.paid = true
        if (!registrationInstance.save(flush: true)) {
            registrationInstance.errors.each {println it}
            ret["status"] = "failure"
            ret["message"] = "Can't save registration!"
        }
        else {
            user.save()
            ret = [status: "success", user: user.emailAddress, level: regLevel.toString(), guid: registrationInstance.uuid, event: event.toString(), paid: true]
        }

        if (!user.registrationComplete) {
            //Send confirmation email
            try {
               sendMail {
                   to user.emailAddress
                   subject "${event.toString()} Registration Confirmation"
                   html "<p>Thank you for your registration for ${event.toString()}!  We have created a user for you with temporary credentials given below.  If you need to register with our service again in the future, you will use this username and password.  You can go <a href="">here</a> to change your password though.</p>.<br/><br/>Username: ${user.emailAddress}<br/>Password: ${user.password}"
               }
            } catch (Exception e) {
                println "Unable to send confirmation email!"
                ret["status"] = "failure"
                ret["message"] = "Unable to send confirmation email!"
            }
        }

        withFormat {
            json {render ret as JSON}
            js   {render "${params.callback}(${ret as JSON})"}
        }
    }
}