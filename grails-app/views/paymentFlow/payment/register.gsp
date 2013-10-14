<%@ page import="com.swag.registration.domain.Registration" %>
<%@ page import="com.swag.registration.domain.RegistrationLevel" %>
<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Level</title>
    </head>
    <body>
        <h3>Choose Registration Level</h3>
        <g:if test="${message}">
            <div class="message" role="status">${message}</div>
        </g:if>
        <div class="round">
	        <g:form>
	            <g:select id="registrationLevel" name="regLevelId" from="${event.levels}" optionKey="id" required="" class="many-to-one"/>
	            <g:submitButton name="continue" value="Continue"/>
	        </g:form>
        </div>
    </body>
</html>