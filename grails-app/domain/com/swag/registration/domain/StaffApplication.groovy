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
			this.position.user = user
			this.position.available = false
			this.approvedBy = approvedBy
			this.approvedOn = new Date()
			this.position.save()
			this.save()
		}
	}

	@Override
	public Event getOwner() {
		return position.event
	}
}
