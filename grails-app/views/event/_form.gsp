<%@ page import="com.swag.registration.domain.Event" %>



<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="event.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${eventInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'homePage', 'error')} ">
	<label for="homePage">
		<g:message code="event.homePage.label" default="Home Page" />
		
	</label>
	<g:textField name="homePage" value="${eventInstance?.homePage}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'year', 'error')} ">
	<label for="year">
		<g:message code="event.year.label" default="Year" />
		
	</label>
	<g:textField name="year" value="${eventInstance?.year}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'numeral', 'error')} ">
	<label for="numeral">
		<g:message code="event.numeral.label" default="Numeral" />
		
	</label>
	<g:textField name="numeral" value="${eventInstance?.numeral}"/>
</div>