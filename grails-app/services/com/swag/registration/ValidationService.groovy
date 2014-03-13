package com.swag.registration

import grails.converters.JSON
import grails.transaction.Transactional

import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError

@Transactional
class ValidationService {
	def grailsApplication
	MessageSource messageSource
	
	def validate(String domainClassName, Map properties) {
		Locale locale = Locale.getDefault()
		Class clazz = grailsApplication.domainClasses.find { it.clazz.simpleName == domainClassName }.clazz
		def object = clazz.newInstance(properties)
		
		Boolean result = object.validate(properties.keySet() as List)
		Map resultMap = [success: result, fields: [:]]
		
		properties.keySet().each { field ->
			FieldError error = object.errors.getFieldError(field)
			if (error) {
				String message = messageSource.getMessage(error, locale)
				resultMap["fields"].putAt(field, message)
			}
		}
		
		return resultMap as JSON
	}
}
