
<%@ page import="com.swag.registration.domain.RegistrationLevel" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'registrationLevel.label', default: 'RegistrationLevel')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-registrationLevel" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="show-registrationLevel" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list registrationLevel">
                          
              	<g:if test="${registrationLevelInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="registrationLevel.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${registrationLevelInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${registrationLevelInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="registrationLevel.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${registrationLevelInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${registrationLevelInstance?.event}">
				<li class="fieldcontain">
					<span id="event-label" class="property-label"><g:message code="registrationLevel.event.label" default="Event" /></span>
					
						<span class="property-value" aria-labelledby="event-label"><g:link controller="event" action="show" id="${registrationLevelInstance?.event?.id}">${registrationLevelInstance?.event?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${registrationLevelInstance?.price}">
				<li class="fieldcontain">
					<span id="price-label" class="property-label"><g:message code="registrationLevel.price.label" default="Price" /></span>
					
						<span class="property-value" aria-labelledby="price-label"><g:formatNumber number="${registrationLevelInstance?.price}" type="currency" currencyCode="USD" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${registrationLevelInstance?.validFor}">
				<li class="fieldcontain">
					<span id="validFor-label" class="property-label"><g:message code="registrationLevel.validFor.label" default="Valid For" /></span>
					
						<span class="property-value" aria-labelledby="validFor-label"><g:fieldValue bean="${registrationLevelInstance}" field="validFor"/></span>
					
				</li>
				</g:if>
                          
                <g:if test="${registrationLevelInstance?.preRegOffers}">
				<li class="fieldcontain">
					<span id="preRegOffers-label" class="property-label"><g:message code="registrationLevel.preRegOffers.label" default="Pre-Reg Offers" /></span>
					
						<g:each in="${registrationLevelInstance.preRegOffers}" var="b">
						<span class="property-value" aria-labelledby="benefits-label"><g:link controller="preRegistrationOffer" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
                                        <g:hiddenField name="id" value="${registrationLevelInstance?.id}" />
                                        <g:hiddenField name="eventId" value="${registrationLevelInstance?.event.id}" />
					<g:link class="edit" action="edit" id="${registrationLevelInstance?.id}" params="${[levelId: registrationLevelInstance?.id, eventId: registrationLevelInstance?.event.id]}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                                        <g:link controller="preRegistrationOffer" action="create" params="${[levelId: registrationLevelInstance?.id, eventId: registrationLevelInstance?.event.id]}">Add Pre-Registration Offer</g:link>
                                        <g:link controller="benefit" action="addBenefit" params="${[eventId: registrationLevelInstance?.event.id,levelId: registrationLevelInstance?.id]}">Add Benefit</g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
