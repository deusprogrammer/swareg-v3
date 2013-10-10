package com.swag.registration.domain

class RegistrationLevel implements Serializable, EventChildObject {
    String name
    String description
    String validFor
    Double price

    boolean needAdmin = false

    static hasMany = [preRegOffers: PreRegistrationOffer]
    static belongsTo = [event: Event]

    String toString() {
        return "${name}: ${description} (${asMoney(currentPrice)})"
    }

    def getCurrentPrice() {
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

    private static String asMoney(def value) {
        String svalue = value.toString()
        String[] number = svalue.split("\\.")
        String fraction = "00"
        String whole = svalue

        if (number.length > 1) {
            if (number[1].length() < 2) {
                whole = number[0]
                fraction = number[1] + "0"
            }
        }

        return "\$" + whole + "." + fraction
    }

    static constraints = {
    }

    static mapping = {sort "price"}

	@Override
	public Event getOwner() {
		return event
    }
}