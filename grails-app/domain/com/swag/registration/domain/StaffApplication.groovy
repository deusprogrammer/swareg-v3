package com.swag.registration.domain

import com.swag.registration.security.User

class StaffApplication implements EventChildObject {
	StaffPosition position
	User user
	User approvedBy
	Date approvedOn = new Date()
	String message
	
	static belongsTo = [position: StaffPosition]

    static constraints = {
		message nullable: true, blank: true
		approvedBy nullable: true
		approvedOn nullable: true
    }
	
	public void approve(User approvedBy) {
		if (position.available) {
			position.assign(this.user)
			this.approvedBy = approvedBy
			this.approvedOn = new Date()
			this.save()
		}
	}
	
	public User decline() {
		User declinedUser = user
		this.delete()
		return declinedUser
	}

	@Override
	public Event getOwner() {
		return position.event
	}
}
