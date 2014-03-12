package com.swag.registration.security

import com.swag.registration.*

class Activation implements Serializable {
    static transient emailService
    
    User user
    String token
    
    public String toString() {
        return "${user} <${token}>"
    }
    
    public static Activation create(String emailAddress, Boolean sendEmail = false) {
        String token = UUID.randomUUID().toString()
        User user = new User(username: emailAddress, emailAddress: emailAddress, password: token, enabled: false)
        Role roleUser = Role.findByAuthority('ROLE_USER')
        if (!user.save(flush: true)) {
            print user.errors
            return null
        }
        UserRole.create user, roleUser, true
        return create(user, sendEmail)
    }
    
    public static Activation create(User user, Boolean sendEmail = false) {
        Activation activation = new Activation(user: user, token: UUID.randomUUID().toString())
        if(!activation.save(flush: true)) {
            return null
        }
        
        if (sendEmail) {
            emailService.sendOnsiteOrderEmail(activation)
        }
        
        return activation
    }
    
    public void activate(String password) {

    }

    static constraints = {
        user unique: true
    }
}
