package com.swag.registration

import com.swag.registration.email.RequestCredentialsMailSender

class SetupUpdaterService {
	def grailsApplication
	
    def updateEmail(String username, String password) {
		RequestCredentialsMailSender.overrideCreds(username, password)
    }
}
