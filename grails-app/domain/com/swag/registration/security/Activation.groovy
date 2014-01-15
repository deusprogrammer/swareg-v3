package com.swag.registration.security

class Activation implements Serializable {
	User user
	String token
	
	public String toString() {
		return "${user} <${token}>"
	}
	
	public static Activation create(String emailAddress) {
		String token = UUID.randomUUID().toString()
		User user = new User(username: emailAddress, emailAddress: emailAddress, password: token, enabled: false)
		Role roleUser = Role.findByAuthority('ROLE_USER')
		if (!user.save(flush: true)) {
			print user.errors
			return null
		}
		UserRole.create user, roleUser, true
		return create(user)
	}
	
	public static Activation create(User user) {
		return new Activation(user: user, token: UUID.randomUUID().toString())
	}
	
	public void activate(String password) {

	}

    static constraints = {
		user unique: true
    }
}
