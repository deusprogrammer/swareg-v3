package com.swag.registration.domain

import com.swag.registration.security.User

class StaffPosition implements EventChildObject {
	User user
	String title
	Byte permissions
	Boolean available = true
	
	static belongsTo = [event: Event] 

    static constraints = {
		user nullable: true
    }
	
	public void apply(User user, String message) {
		StaffApplication application = new StaffApplication(position: this, user: user, message: message)
		application.save()
	}

	@Override
	public Event getOwner() {
		return event
	}
}