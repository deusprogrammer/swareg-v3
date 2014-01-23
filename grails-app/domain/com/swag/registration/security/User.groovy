package com.swag.registration.security

import com.swag.registration.*
import com.swag.registration.domain.*
import grails.plugins.springsecurity.SpringSecurityService
import java.security.MessageDigest

class User implements Serializable {
	transient emailService
    transient springSecurityService
	
    // Spring Security variables
    String username
    String password
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false

    // Name
    String firstName
    String lastName

    // Billing address
    String streetAddress1
    String streetAddress2
    String city
    String state
    String zipCode
    String countryCode
    String emailAddress

    // Personal information
    String gender
    Integer age

    // Registration info
    boolean registrationComplete = true

    static hasMany = [registrations: Registration, events: Event, positions: StaffPosition]

    public String toString() {
        return emailAddress
    }

    static constraints = {
        username blank: false, unique: true
        password blank: false

        username nullable: true, unique: true
        firstName nullable: true
        lastName nullable: true
        password nullable: true
        streetAddress1 nullable: true
        streetAddress2 nullable: true
        city nullable: true
        state nullable: true
        zipCode nullable: true
        countryCode nullable: true
        gender nullable: true
        age nullable: true
        emailAddress email: true, unique: true
    }

    static mapping = {
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword2()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword2()
        }
    }

    def testPassword(String password) {
        return this.password == springSecurityService.encodePassword(password)
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }
	
	// Hard coding the necessary encoding to make hibernate and webflows happy
	protected void encodePassword2() {
		password = sha256Hash(password)
	}
	
	// Hard coding the necessary encoding to make hibernate and webflows happy
	protected static String sha256Hash(String value) {
		return org.apache.commons.codec.digest.DigestUtils.sha256Hex(value) 
	}
}
