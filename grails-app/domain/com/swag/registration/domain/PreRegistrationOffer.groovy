package com.swag.registration.domain

class PreRegistrationOffer implements Serializable, EventChildObject {
    Date startDate
    Date endDate
    Double price

    def stringFormatterService

    String toString() {
        return "[${startDate.format('MM/dd/yy')}" + " to " + "${endDate.format('MM/dd/yy')}]" + " (${asMoney(price)})"
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

    static belongsTo = [registrationLevel: RegistrationLevel]

    static constraints = {
    }

	@Override
	public Event getOwner() {
		return registrationLevel.event;
	}
}
