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
	            <fieldset class="flow">
	                <legend>Change Password</legend>
		            <table>
		                <tr><td>Password</td><td><g:passwordField name="password" id="password1" /></td></tr>
		                <tr><td></td><td><g:passwordField name="confirmPassword" id="password2" /></td></tr>
		            </table>
		            <g:submitButton name="set" value="Set"/>
	            </fieldset>
	        </g:form>
        </div>
    </body>
</html>