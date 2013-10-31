package com.swag.registration.domain

class Addon {
	String imageLocation
	String name
	String sku
	Double price
	
	static belongsTo = [event: Event]

    static constraints = {
    }
}
