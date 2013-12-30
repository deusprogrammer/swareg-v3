package com.swag.registration.domain
import com.swag.registration.security.*

class Gift {
	User giver
	User receiver
	Registration badge
	
	Date expires
	
	static Gift create(String emailAddress, Registration badge) {
		User user = User.findByEmailAddress(emailAddress)
		
		if (!user) {
			user = new User(emailAddress: emailAddress, password: UUID.randomUUID().toString(), enabled: true)
			Role roleUser = Role.findByAuthority('ROLE_USER')
			UserRole.create user, roleUser, true
			if (!user.save()) {
				print user.errors
				return null
			}
		}
		
		Gift gift = new Gift(giver: badge.user, receiver: user, badge: badge, expires: new Date() + 30)
		
		if (!gift.save(flush: true)) {
			print gift.errors
			return null
		}
		
		return gift
	}
	
	static List getReceived(User user) {
		return Gift.list().findAll {
			Gift gift ->
				gift.receiver.emailAddress == user.emailAddress
		}
	}
	
	static List getGiven(User user) {
		return Gift.list().findAll {
			Gift gift ->
				gift.giver.emailAddress == user.emailAddress
		}
	}
	
	public void completeTransfer() {
		badge.user = receiver
		badge.save(flush: true)
	}

    static constraints = {
    }
}