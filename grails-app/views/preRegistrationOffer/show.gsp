
<%@ page import="com.swag.registration.domain.PreRegistrationOffer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'preRegistrationOffer.label', default: 'PreRegistrationOffer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-preRegistrationOffer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-preRegistrationOffer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list preRegistrationOffer">
			
				<g:if test="${preRegistrationOfferInstance?.endDate}">
				<li class="fieldcontain">
					<span id="endDate-label" class="property-label"><g:message code="preRegistrationOffer.endDate.label" default="End Date" /></span>
					
						<span class="property-value" aria-labelledby="endDate-label"><g:formatDate date="${preRegistrationOfferInstance?.endDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${preRegistrationOfferInstance?.price}">
				<li class="fieldcontain">
					<span id="price-label" class="property-label"><g:message code="preRegistrationOffer.price.label" default="Price" /></span>
					
						<span class="property-value" aria-labelledby="price-label"><g:formatNumber number="${preRegistrationOfferInstance?.price}" type="currency" currencyCode="USD" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${preRegistrationOfferInstance?.registrationLevel}">
				<li class="fieldcontain">
					<span id="registrationLevel-label" class="property-label"><g:message code="preRegistrationOffer.registrationLevel.label" default="Registration Level" /></span>
					
						<span class="property-value" aria-labelledby="registrationLevel-label"><g:link controller="registrationLevel" action="show" id="${preRegistrationOfferInstance?.registrationLevel?.id}">${preRegistrationOfferInstance?.registrationLevel?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${preRegistrationOfferInstance?.startDate}">
				<li class="fieldcontain">
					<span id="startDate-label" class="property-label"><g:message code="preRegistrationOffer.startDate.label" default="Start Date" /></span>
					
						<span class="property-value" aria-labelledby="startDate-label"><g:formatDate date="${preRegistrationOfferInstance?.startDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${preRegistrationOfferInstance?.id}" />
                                        <g:hiddenField name="eventId" value="${preRegistrationOfferInstance?.registrationLevel.event.id}" />
					<g:link class="edit" action="edit" id="${preRegistrationOfferInstance?.id}" params="${[eventId: preRegistrationOfferInstance?.registrationLevel.event.id]}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>