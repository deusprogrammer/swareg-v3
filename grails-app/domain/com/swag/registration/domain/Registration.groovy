package com.swag.registration.domain

import java.util.UUID
import com.swag.registration.security.User

class Registration implements Serializable, Payable, EventChildObject {
    String uuid
    String receiptNumber
    RegistrationLevel registrationLevel
    Boolean paid = false
    Payment payment

    static belongsTo = [user: User, event: Event]

    String toString() {
        return "${event.name}[${registrationLevel.name}]- ${user.username}"
    }

    transient beforeInsert() {
        uuid = UUID.randomUUID().toString()
        if (!receiptNumber) {
            receiptNumber = uuid
        }
    }

    static constraints = {
        registrationLevel nullable: true
        receiptNumber nullable: true
        uuid nullable: true
        payment nullable: true
    }

    @Override
    public Double getPrice() {
        return registrationLevel.getCurrentPrice()
    }

    @Override
    public Double getTaxRate() {
        return event.taxRate
    }

    @Override
    public User getPurchaser() {
        return user
    }

    @Override
    public Currency getCurrency() {
        return event.currency
    }

    @Override
    public String getDescription() {
        return "${registrationLevel.name} registration for ${event.name}";
    }

    @Override
    public Event getOwner() {
        return event
    }

	@Override
	public Boolean isPaid() {
		return paid;
	}
}
