package com.swag.registration.domain

class Payment implements Serializable {
    String creditCardNumber = ""
    String paymentId = ""
    String status = ""
	String debugId = ""

    static constraints = {
        creditCardNumber nullable: true
        paymentId nullable: true
        status nullable: true
		debugId nullable: true
    }
}