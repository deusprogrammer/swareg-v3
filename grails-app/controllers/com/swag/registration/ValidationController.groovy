package com.swag.registration

class ValidationController {
	ValidationService validationService
	
    def validate() {
		println "VALIDATE CALLED!"
		
		Map map = request.JSON
		
		println map
		
		String domain = map["domain"]?.capitalize()
		Map fields    = map["fields"]
		
		if (!domain || fields.empty) {
			response.setStatus(404)
			return
		}
		
		render validationService.validate(domain, fields)
	}
}