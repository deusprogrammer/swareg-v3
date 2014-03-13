package com.swag.registration

import com.swag.registration.domain.Event

class TestController {

    def index() { 
		def value = params.value
		
		if (value && value.isInteger()) {
			value = value.toInteger()
		}
		
		Event e = new Event(numeral: value)
		print e.validate(["numeral"])
		print e.errors.getFieldError("numeral")
		
		render "You entered ${value}"
	}
}
