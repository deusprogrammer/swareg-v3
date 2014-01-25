package com.swag.registration.domain

import org.springframework.aop.aspectj.RuntimeTestWalker.ThisInstanceOfResidueTestVisitor;

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