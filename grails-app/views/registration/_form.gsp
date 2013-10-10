<%@ page import="com.swag.registration.domain.Registration" %>

<g:hiddenField name="eventId" value="${eventId}" />

<div class="fieldcontain">
  <label>Email Address:</label><g:textField name="emailAddress" value="${registrationInstance?.user?.emailAddress}" />
</div>

<div class="fieldcontain ${hasErrors(bean: registrationInstance, field: 'registrationLevel', 'error')} required">
	<label for="registrationLevel">
		<g:message code="registration.registrationLevel.label" default="Registration Level" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="registrationLevel" name="registrationLevel.id" from="${levels}" optionKey="id" required="" value="${registrationInstance?.registrationLevel?.id}" class="many-to-one"/>
</div>