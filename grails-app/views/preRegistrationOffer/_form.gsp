<%@ page import="com.swag.registration.domain.PreRegistrationOffer" %>

<div class="fieldcontain ${hasErrors(bean: preRegistrationOfferInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="preRegistrationOffer.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="startDate" id="startDate" value="${preRegistrationOfferInstance?.startDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: preRegistrationOfferInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="preRegistrationOffer.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="endDate" id="endDate" value="${preRegistrationOfferInstance?.endDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: preRegistrationOfferInstance, field: 'price', 'error')} ">
	<label for="price">
		<g:message code="preRegistrationOffer.price.label" default="Price" />
		
	</label>
	<g:textField name="price" value="${preRegistrationOfferInstance?.price}"/>
</div>

<g:hiddenField id="registrationLevel" name="registrationLevel.id" value="${levelId}" />
<g:hiddenField id="eventId" name="eventId" value="${eventId}" />