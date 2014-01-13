package com.swag.registration.domain

import com.swag.registration.security.User

class PasswordReset {
	User user
	String token = UUID.randomUUID()
	Date expires = new Date() + 30

    static constraints = {
    }
}