
<%@ page import="com.swag.registration.domain.ConfigHolder" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'configHolder.label', default: 'ConfigHolder')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-configHolder" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-configHolder" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="configKey" title="${message(code: 'configHolder.configKey.label', default: 'Config Key')}" />
					
						<g:sortableColumn property="configValue" title="${message(code: 'configHolder.configValue.label', default: 'Config Value')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${configHolderInstanceList}" status="i" var="configHolderInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${configHolderInstance.id}">${fieldValue(bean: configHolderInstance, field: "configKey")}</g:link></td>
					
						<td>${fieldValue(bean: configHolderInstance, field: "configValue")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${configHolderInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
