<html>
    <head>
        <meta name="layout" content="flow">
        <title>Event:Login</title>
    </head>
    <body>
        <h3>Login</h3>
        <div class="pretext">
            <p>In order to create an event on SWAreG, you must have a user and be logged in.</p>
            <p>Please login below or create a new user</p>
        </div>
        <div class="round">
	        <g:form>
	            <table>
	            <tr><td>Email Address</td><td><g:textField name="emailAddress" /></td></tr>
	            <tr><td>Password</td><td><g:passwordField name="password" /></td></tr>
	            </table>
	            <g:submitButton name="login" value="Login" />
	            <g:submitButton name="createUser" value="New User" />
	        </g:form>
        </div>
    </body>
</html>