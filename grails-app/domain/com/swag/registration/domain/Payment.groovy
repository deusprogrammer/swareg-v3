package com.swag.registration.domain

class Payment implements Serializable {
    String  creditCardNumber = ""
	double  total            = 0.00
	double  subtotal         = 0.00
	double  tax              = 0.00
	String  paymentType      = ""
    String  paymentId        = ""
    String  status           = ""
	boolean completed        = false
	String  debugId          = ""
	
	String transactionId     = ""

    static constraints = {
        creditCardNumber nullable: true
		total nullable: true
		subtotal nullable: true
		tax nullable: true
		paymentType nullable: true
        paymentId nullable: true
        status nullable: true
		completed nullable: true
		debugId nullable: true
		transactionId nullable: true
    }
}