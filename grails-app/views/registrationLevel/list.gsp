
<%@ page import="com.swag.registration.domain.RegistrationLevel" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'registrationLevel.label', default: 'RegistrationLevel')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-registrationLevel" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-registrationLevel" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>

                                                <g:sortableColumn property="name" title="${message(code: 'registrationLevel.name.label', default: 'Name')}" />

						<g:sortableColumn property="description" title="${message(code: 'registrationLevel.description.label', default: 'Description')}" />
					
						<th><g:message code="registrationLevel.event.label" default="Event" /></th>
					
						<g:sortableColumn property="price" title="${message(code: 'registrationLevel.price.label', default: 'Price')}" />
					
						<g:sortableColumn property="validFor" title="${message(code: 'registrationLevel.validFor.label', default: 'Valid For')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${registrationLevelInstanceList}" status="i" var="registrationLevelInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${registrationLevelInstance.id}">${fieldValue(bean: registrationLevelInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: registrationLevelInstance, field: "event")}</td>
					
						<td>${fieldValue(bean: registrationLevelInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: registrationLevelInstance, field: "price")}</td>
					
						<td>${fieldValue(bean: registrationLevelInstance, field: "validFor")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${registrationLevelInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
