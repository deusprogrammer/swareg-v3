package com.swag.registration.domain

import com.swag.registration.security.*
import com.swag.registration.domain.order.*

class RegistrationLevel implements Serializable, EventChildObject {
    String name
    String description
    String validFor
    Double price

    boolean needAdmin = false

    static hasMany = [preRegOffers: PreRegistrationOffer]
    static belongsTo = [event: Event]

    String toString() {
        return "${event}[${name}]: ${description} (${String.format("\$%.2f",currentPrice)})"
    }
	
	public Registration generateRegistration(User user, PayPalOrder order) {
		Registration reg = new Registration([
			registrationLevel: this,
			event: event,
			order: order,
			user: user
		])
		
		reg.save()
		user.addToRegistrations(reg)
		return reg
	}
	
	public Registration generateRegistration(Map map) {
		return generateRegistration(map["user"], map["order"])
	}
	
	@Override
	public String getDescription() {
		return "${name} registration for ${event.name}";
	}

    public Double getCurrentPrice() {
        def now = new Date()
        def price
        PreRegistrationOffer offer = this.preRegOffers?.find{ PreRegistrationOffer offer -> offer.startDate <= now && offer.endDate >= now}

        if (!offer) {
            price = this.price
        }
        else {
            price = offer.price
        }

        return price
    }

    static constraints = {
    }

    static mapping = {sort "price"}

	@Override
	public Event getOwner() {
		return event
    }
}