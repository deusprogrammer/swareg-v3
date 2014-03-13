package com.swag.registration

class ValidationController {
	ValidationService validationService
	
    def validate() {
		Map map = request.JSON
		String domain = map["domain"].capitalize()
		println domain
		render validationService.validate(domain, map["fields"])
	}
}