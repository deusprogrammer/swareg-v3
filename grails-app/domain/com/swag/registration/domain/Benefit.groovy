package com.swag.registration.domain

class Benefit {
    String name
    String description

    String toString() {
        return name
    }

    static belongsTo = [registrationLevel: RegistrationLevel, event: Event]

    static constraints = {
    }
}
