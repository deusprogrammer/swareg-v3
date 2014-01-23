package com.swag.registration.domain

import com.swag.registration.security.User

class StaffApplication implements EventChildObject {
	StaffPosition position
	User user
	String message

    static constraints = {
		message nullable: true, blank: true
    }
	
	public void approve() {
		if (position.available) {
			this.position.user = user
			this.position.available = false
			this.delete()
		}
	}

	@Override
	public Event getOwner() {
		return position.event
	}
}
