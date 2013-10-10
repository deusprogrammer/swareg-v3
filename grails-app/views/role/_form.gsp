<%@ page import="com.swag.registration.domain.Role" %>



<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="role.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${com.swag.registration.domain.Event.list()}" optionKey="id" required="" value="${roleInstance?.event?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'role', 'error')} required">
	<label for="role">
		<g:message code="role.role.label" default="Role" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="role" from="${com.swag.registration.domain.UserRole?.values()}" keys="${com.swag.registration.domain.UserRole.values()*.name()}" required="" value="${roleInstance?.role?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="role.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.swag.registration.domain.User.list()}" optionKey="id" required="" value="${roleInstance?.user?.id}" class="many-to-one"/>
</div>

