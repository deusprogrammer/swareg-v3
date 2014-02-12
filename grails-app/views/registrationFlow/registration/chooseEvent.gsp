<%@ page import="com.swag.registration.domain.Event" %>
<html>
    <head>
        <meta name="layout" content="new">
        <title>Registration:Choose-Event</title>
    </head>
    <body>
        <h3>Please Select An Event</h3>
        <div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>For whatever reason, the site you redirected from didn't include their event id.  So please choose which event you were trying to register for.</p>
            </div>
        </div>
        <div class="dash rounded skinny-scaled spaced">
        	<span class="legend">Event</span>
        	<div class="inner-x">
		        <g:form>
		            <table class="pairs">
		               <tr><td>Event</td><td><g:select name="event" from="${Event.list()}" keys="${Event.list()*.id}" /></td>
		            </table>
	                <g:submitButton name="select" value="Continue with this event" />
		        </g:form>
	        </div>
        </div>
    </body>
</html>