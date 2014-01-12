package com.swag.registration

class SetupUpdaterService {
	def grailsApplication
	
    def updateEmail(String username, String password) {
		grailsApplication.config.grails.mail.username = username
		grailsApplication.config.grails.mail.password = password
    }
}
