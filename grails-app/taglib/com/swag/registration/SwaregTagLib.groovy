package com.swag.registration

import grails.plugins.springsecurity.SpringSecurityService
import com.swag.registration.domain.Event

class SwaregTagLib {
    SpringSecurityService springSecurityService

    def displayEvents = { attrs, body ->
        Event.list().each { Event event ->
            println "EVENT OWNER: ${event.owner}"
            println "CURRNET:     ${springSecurityService.currentUser}"
            println "SAME:        ${event.user == springSecurityService.currentUser}"
            if (event.user == springSecurityService.currentUser) {
                out << "<a href='" + createLink(controller: "event", action: "show", id: event.id) + "'><button>${event}</button></a><br/>"
            }
        }
    }
}
