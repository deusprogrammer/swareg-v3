<html>
    <head>
        <meta name="layout" content="flow">
        <title>Setup:Password</title>
    </head>
    <body>
        <h3>Please Enter a Root Password</h3>
        <div class="pretext">
        <p>This system uses a root user named "global_admin" to act as a user that can read/write/delete anything.</p>
        <p>Please set the password for this user.</p>
        </div>
        <div class="round">
	        <g:form>
	            <table>
	                <tr><td>Password</td><td><g:passwordField name="password" /></td></tr>
	                <tr><td>Confirm</td><td><g:passwordField name="confirmPassword" /></td></tr>
	            </table>
	            <g:submitButton name="set" value="Set"/>
	        </g:form>
        </div>
    </body>
</html>