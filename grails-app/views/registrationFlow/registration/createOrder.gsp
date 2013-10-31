<%@ page import="com.swag.registration.domain.Registration" %>
<%@ page import="com.swag.registration.domain.RegistrationLevel" %>
<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Level</title>
    </head>
    <body>
        <h3>Choose Registration Level</h3>
        <div class="pretext">
            <p>Now choose how many badges you want.  Each badge level comes with different benefits for you, the attendee.</p>
            <p>Please note that we tie your user to each badge you buy, so any additional badges will have to be transferred to their owners, otherwise they will all tie to your account.</p>
        </div>
        <div class="round">
	        <g:form>
	            <table>
		            <g:each in="${event.levels}" var="level">
		                <tr><td>${level.name}</td><td>${String.format("\$%.2f",level.currentPrice)}</td><td><g:field size="2" type="number" name="levels.${level.id}.quantity" /></td></tr>
	                </g:each>
                </table>
	            <g:submitButton name="continue" value="Continue"/>
	        </g:form>
        </div>
    </body>
</html>