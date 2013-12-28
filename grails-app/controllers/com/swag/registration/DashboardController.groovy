package com.swag.registration
import com.swag.registration.domain.*
import grails.plugins.springsecurity.SpringSecurityService

class DashboardController {
	SpringSecurityService springSecurityService
	
    def index() {
		if (!springSecurityService.loggedIn) {
			return
		}
		
		List badges = Registration.list().findAll {
			Registration it ->
				it.user.emailAddress == springSecurityService.currentUser.emailAddress
		}.sort {
			Registration it ->
				it.id
		}
		List events = Event.list().findAll {
			Event it ->
				it.user.emailAddress == springSecurityService.currentUser.emailAddress
		}.sort {
			Event it ->
				it.id
		}
		
		return [badges: badges, events: events]
	}
}
