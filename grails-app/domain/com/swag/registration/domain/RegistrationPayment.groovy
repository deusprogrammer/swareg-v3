package com.swag.registration.domain

class RegistrationPayment implements Serializable {
    String creditCardNumber = ""
    String paymentId = ""
    String status = ""

    static constraints = {
        creditCardNumber nullable: true
        paymentId nullable: true
        status nullable: true
    }
}
