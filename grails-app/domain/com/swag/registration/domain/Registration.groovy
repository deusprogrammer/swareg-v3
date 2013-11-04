package com.swag.registration.domain

import java.util.UUID

import com.swag.registration.domain.order.PayPalOrder;
import com.swag.registration.security.User

class Registration implements Serializable, EventChildObject {
    RegistrationLevel registrationLevel
    PayPalOrder order

    static belongsTo = [user: User, event: Event]

    String toString() {
        return "${event}[${registrationLevel.name}]- ${user.username}"
    }

    static constraints = {
        registrationLevel nullable: true
        order nullable: true
        user nullable: true
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
        return order ? order.completed : false
    }
}
