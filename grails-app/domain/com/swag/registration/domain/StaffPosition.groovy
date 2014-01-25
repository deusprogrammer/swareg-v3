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
	
	public Map unpackPermissions() {
		Map unpacked = [:]
		List bits = []
		
		Byte mask = 0x01
		
		Byte p = permissions
		
		4.times {
			bits.add(0)
		}
		
		Integer i = 0
		while (p > 0) {
			Byte b = p & mask
			bits.set(i, b)
			p = p >> 1
			i++
		}
		
		unpacked["read"]  = bits.get(0) == 1
		unpacked["write"] = bits.get(1) == 1
		unpacked["admin"] = bits.get(2) == 1
		
		return unpacked
	}
	
	public boolean apply(User user, String message) {
		StaffApplication application = new StaffApplication(position: this, user: user, message: message)
		if (!application.save()) {
			return false
		}
		
		return true
	}
	
	public boolean assign(User user) {
		this.user = user
		this.available = false
		if (!this.save()) {
			return false
		}
		
		return true
	}
	
	public boolean unassign() {
		this.user = null
		this.available = true
		if (!this.save()) {
			return false
		}
		
		return true
	}

	@Override
	public Event getOwner() {
		return event
	}
}