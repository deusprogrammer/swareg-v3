<%@ page import="com.swag.registration.domain.Registration" %>
<%@ page import="com.swag.registration.domain.RegistrationLevel" %>
<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Manual-Registration</title>
    </head>
    <body>
        <h3>Choose Registration Level</h3>
        <div class="pretext">
            <p>Enter an email address and choose a registration level.</p>
        </div>
        <div class="round">
            <g:form>
                <label>Email Address:</label><g:textField id="emailAddress" name="emailAddress" />
                <label>Registration: </label><g:select id="registrationLevel" name="regLevelId" from="${event.levels}" optionKey="id" required="" class="many-to-one"/>
                <g:submitButton name="continue" value="Continue"/>
            </g:form>
        </div>
    </body>
</html>