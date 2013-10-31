package com.swag.registration.domain

class Payment implements Serializable {
    String  creditCardNumber = ""
	
	Double  total            = 0.00
	Double  subtotal         = 0.00
	Double  tax              = 0.00
	Currency currency        = Currency.USD	
	
	String  paymentType      = ""
    String  paymentId        = ""
    String  status           = ""
	Boolean completed        = false
	String  debugId          = ""
	
	String transactionId     = ""
	
	static belongsTo = [registration: Registration]
	static hasMany = [items: PaymentItem]

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