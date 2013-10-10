
<%@ page import="com.swag.registration.domain.Event" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-event" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="show-event" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list event">
			
				<g:if test="${eventInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="event.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${eventInstance}" field="name"/></span>
					
				</li>
				</g:if>
                          
				<g:if test="${eventInstance?.year}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="event.year.label" default="Year" /></span>
					
						<span class="property-value" aria-labelledby="year-label"><g:fieldValue bean="${eventInstance}" field="year"/></span>
					
				</li>
				</g:if>
                          
                <g:if test="${eventInstance?.numeral}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="event.numeral.label" default="Numeral" /></span>
					
						<span class="property-value" aria-labelledby="numeral-label"><g:fieldValue bean="${eventInstance}" field="numeral"/></span>
					
				</li>
				</g:if>
                          
                <g:if test="${eventInstance?.homePage}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="event.homePage.label" default="Home Page" /></span>
					
						<span class="property-value" aria-labelledby="homePage-label"><g:fieldValue bean="${eventInstance}" field="homePage"/></span>
					
				</li>
				</g:if>
                          
		
				<g:if test="${eventInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="event.user.label" default="Owner" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${eventInstance?.user?.id}">${eventInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
                          
                <g:if test="${eventInstance?.uuid}">
				<li class="fieldcontain">
					<span id="uuid-label" class="property-label"><g:message code="event.uuid.label" default="Event Id" /></span>
					
						<span class="property-value" aria-labelledby="uuid-label">${eventInstance?.uuid}</span>
					
				</li>
				</g:if>
                          
                <g:if test="${eventInstance?.apiKey}">
				<li class="fieldcontain">
					<span id="apiKey-label" class="property-label"><g:message code="event.apiKey.label" default="API Key" /></span>
					
						<span class="property-value" aria-labelledby="apiKey-label">${eventInstance?.apiKey}</span>
					
				</li>
				</g:if>
			
                <g:if test="${eventInstance?.levels}">
				<li class="fieldcontain">
					<span id="levels-label" class="property-label"><g:message code="event.levels.label" default="Registration Levels" /></span>
					
						<g:each in="${eventInstance.levels}" var="r">
						<span class="property-value" aria-labelledby="levels-label"><g:link controller="registrationLevel" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${eventInstance?.id}" />
					<g:link controller="registration" action="listByEvent" id="${eventInstance.id}">List Registrations</g:link>
					<g:link controller="registrationLevel" action="create" params="${[eventId: eventInstance?.id]}">Add Registration Level</g:link>
					<g:link action="edit" id="${eventInstance?.id}">Edit</g:link>
				</fieldset>
			</g:form>
                </div>
	</body>
</html>