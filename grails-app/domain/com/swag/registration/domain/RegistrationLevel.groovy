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
        //return "${event}[${name}] (${String.format("%.2f",currentPrice)} ${event.currency})"
        return "${event}[${name}]"
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
            return "${name} registration for ${event?.name}";
    }

    public Double getCurrentPrice() {
        def now = new Date()
        def price
        ArrayList<PreRegistrationOffer> offers = this.preRegOffers?.findAll{ PreRegistrationOffer offer -> offer.startDate <= now && offer.endDate >= now}

        PreRegistrationOffer offer = null
        
        // Find lowest price
        offers.each { PreRegistrationOffer it ->
            if (!offer || it.price < offer.price) {
                offer = it
            }
        }
        
        // If no offers found, then the price is the default
        if (!offer) {
            price = this.price
        } else {
            price = offer.price
        }

        return price
    }
    
    public String getCurrentPriceString() {
        return "${currentPrice}${event.currency.escapeCode}"
    }

    static constraints = {
    }

    static mapping = {sort "price"}

    @Override
    public Event getOwner() {
        return event
    }
}