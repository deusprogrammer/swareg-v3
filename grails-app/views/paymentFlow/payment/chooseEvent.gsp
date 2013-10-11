<%@ page import="com.swag.registration.domain.Event" %>
<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Choose-Event</title>
    </head>
    <body>
        <h3>No Event Selected</h3>
        <g:if test="${message}">
            <div class="message" role="status">${message}</div>
        </g:if>
        <g:form>
            <label>Event</label><g:select name="event" from="${Event.list()}" keys="${Event.list()*.id}" /><br/>
            <g:submitButton name="select" value="Continue with this event" />
        </g:form>
    </body>
</html>