package com.swag.registration.domain

import com.swag.registration.security.User

class StaffPosition implements EventChildObject {
	User user
	String title
	String description
	Byte permissions
	Boolean available = true
	
	static belongsTo = [event: Event]
	static hasMany = [applicants: StaffApplication]

    static constraints = {
		user nullable: true
    }
	
	public boolean apply(User user, String message) {
		StaffApplication application = new StaffApplication(position: this, user: user, message: message)
		if (!application.save()) {
			return false
		}
		
		return true
	}

	@Override
	public Event getOwner() {
		return event
	}
}