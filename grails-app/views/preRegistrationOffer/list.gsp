
<%@ page import="com.swag.registration.domain.PreRegistrationOffer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'preRegistrationOffer.label', default: 'PreRegistrationOffer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-preRegistrationOffer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-preRegistrationOffer" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="endDate" title="${message(code: 'preRegistrationOffer.endDate.label', default: 'End Date')}" />
					
						<g:sortableColumn property="price" title="${message(code: 'preRegistrationOffer.price.label', default: 'Price')}" />
					
						<th><g:message code="preRegistrationOffer.registrationLevel.label" default="Registration Level" /></th>
					
						<g:sortableColumn property="startDate" title="${message(code: 'preRegistrationOffer.startDate.label', default: 'Start Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${preRegistrationOfferInstanceList}" status="i" var="preRegistrationOfferInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${preRegistrationOfferInstance.id}">${fieldValue(bean: preRegistrationOfferInstance, field: "endDate")}</g:link></td>
					
						<td>${fieldValue(bean: preRegistrationOfferInstance, field: "price")}</td>
					
						<td>${fieldValue(bean: preRegistrationOfferInstance, field: "registrationLevel")}</td>
					
						<td><g:formatDate date="${preRegistrationOfferInstance.startDate}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${preRegistrationOfferInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
