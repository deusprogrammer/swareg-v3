package com.swag.registration.domain.order

import com.swag.registration.domain.Currency;
import com.swag.registration.domain.Registration;

class Order implements Serializable {
    String  creditCardNumber = ""
	
	String  paymentType      = ""
    String  paymentId        = ""
    String  status           = ""
	Boolean completed        = false
	String  debugId          = ""
	
	String transactionId     = ""
	
	static belongsTo = [registration: Registration]
	static hasMany = [items: RegistrationOrderItem]
	
	public Double getTotal() {
		return 0.00
	}
	
	public Double getSubtotal() {
		return 0.00
	}
	
	public Double getTax() {
		return 0.00
	}
	
	public Double getFees() {
		return 0.00
	}

    static constraints = {
        creditCardNumber nullable: true
		paymentType nullable: true
        paymentId nullable: true
        status nullable: true
		completed nullable: true
		debugId nullable: true
		transactionId nullable: true
    }
}