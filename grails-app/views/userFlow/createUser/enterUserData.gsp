<html>
    <head>
        <meta name="layout" content="flow">
        <title>User:Data</title>
    </head>
    <body>
        <h3>User Information</h3>
        <div class="pretext">
            <p>Let's begin.  Please enter your user information.  All information is required.</p>
        </div>
        <div class="round">
	        <g:form>
	            <table>
		            <tr><td>Email</td><td><g:textField name="emailAddress" value="${userData?.emailAddress}"/></td></tr>
		            <tr><td>Password</td><td><g:passwordField name="password1" value="${userData?.password}" /></td></tr>
		            <tr><td></td><td><g:passwordField name="password2" value="${userData?.password}" /></td></tr>
		            <tr><td>First Name</td><td><g:textField name="firstName" value="${userData?.firstName}"/></td></tr>
		            <tr><td>Last Name</td><td><g:textField name="lastName" value="${userData?.lastName}"/></td></tr>
	            </table>
	            <g:submitButton name="next" value="Next"/>
	        </g:form>
        </div>
    </body>
</html>