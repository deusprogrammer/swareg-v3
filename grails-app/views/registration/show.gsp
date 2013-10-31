
<%@ page import="com.swag.registration.domain.Registration" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'registration.label', default: 'Registration')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-registration" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="show-registration" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list registration">
			
				<g:if test="${registrationInstance?.event}">
				<li class="fieldcontain">
					<span id="event-label" class="property-label"><g:message code="registration.event.label" default="Event" /></span>
					
						<span class="property-value" aria-labelledby="event-label"><g:link controller="event" action="show" id="${registrationInstance?.event?.id}">${registrationInstance?.event?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${registrationInstance?.registrationLevel}">
				<li class="fieldcontain">
					<span id="registrationLevel-label" class="property-label"><g:message code="registration.registrationLevel.label" default="Registration Level" /></span>
					
						<span class="property-value" aria-labelledby="registrationLevel-label"><g:link controller="registrationLevel" action="show" id="${registrationInstance?.registrationLevel?.id}">${registrationInstance?.registrationLevel?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${registrationInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="registration.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${registrationInstance?.user?.id}">${registrationInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
				
                <g:if test="${registrationInstance?.order}">
                <li class="fieldcontain">
                    <span id="payment-label" class="property-label"><g:message code="registration.payment.label" default="Order" /></span>
                    
                    <span class="property-value" aria-labelledby="payment-label"><g:link controller="order" action="show" id="${registrationInstance?.order?.id}">${registrationInstance?.order?.paymentId}</g:link></span>
                </li>
                </g:if>
			
			</ol>
		</div>
	</body>
</html>
