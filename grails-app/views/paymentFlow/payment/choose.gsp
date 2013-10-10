<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Login</title>
    </head>
    <body>
        <h3>Registration</h3>
        <g:if test="${message}">
            <div class="message" role="status">${message}</div>
        </g:if>
        <g:form>
            <g:textField name="emailAddress" placeHolder="user@domain.com" /><br/>
	        <g:submitButton name="continue" value="Continue" />
        </g:form>
    </body>
</html>