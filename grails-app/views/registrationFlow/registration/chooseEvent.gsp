<%@ page import="com.swag.registration.domain.Event" %>
<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Choose-Event</title>
    </head>
    <body>
        <h3>Please Select An Event</h3>
        <div class="pretext">
            <p>For whatever reason, the site you redirected from didn't include their event id.  So please choose which event you were trying to register for.</p>
        </div>
        <div class="round">
	        <g:form>
	            <fieldset class="flow">
	                <legend>Event</legend>
		            <table>
		               <tr><td>Event</td><td><g:select name="event" from="${Event.list()}" keys="${Event.list()*.id}" /></td>
		            </table>
	                <g:submitButton name="select" value="Continue with this event" />
                </fieldset>
	        </g:form>
        </div>
    </body>
</html>