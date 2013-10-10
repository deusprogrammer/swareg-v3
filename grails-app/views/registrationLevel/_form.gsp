<%@ page import="com.swag.registration.domain.RegistrationLevel" %>

<div class="fieldcontain ${hasErrors(bean: registrationLevelInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="registrationLevel.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${registrationLevelInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: registrationLevelInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="registrationLevel.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${registrationLevelInstance?.description}"/>
</div>

<g:hiddenField id="event" name="event.id" value="${eventId}"/>

<div class="fieldcontain ${hasErrors(bean: registrationLevelInstance, field: 'price', 'error')} ">
	<label for="price">
		<g:message code="registrationLevel.price.label" default="Price" />
		
	</label>
	<g:textField name="price" value="${registrationLevelInstance?.price}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: registrationLevelInstance, field: 'validFor', 'error')} ">
	<label for="validFor">
		<g:message code="registrationLevel.validFor.label" default="Valid For" />
		
	</label>
	<g:textField name="validFor" value="${registrationLevelInstance?.validFor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: registrationLevelInstance, field: 'needAdmin', 'error')} ">
	<label for="needAdmin">
		<g:message code="registrationLevel.price.label" default="Need Admin?" />
		
	</label>
	<g:checkBox name="needAdmin" value="${registrationLevelInstance?.needAdmin}"/>
</div>