<html>
    <head>
        <meta name="layout" content="flow">
        <title>Password Reset</title>
    </head>
    <body>
        <h3>Enter New Password</h3>
        <div class="pretext">
            <p>Please enter a new password.</p>
        </div>
        <div class="round">
	        <g:form>
	            <fieldset class="flow">
		            <legend>Set New Password</legend>
		            <table>
			            <tr><td>Password</td><td><g:passwordField name="password1" class="password" value="${userData?.password}" /></td></tr>
			            <tr><td></td><td><g:passwordField name="password2" class="password-confirm" value="${userData?.password}" /></td></tr>
		            </table>
		            <g:submitButton name="submit" value="Submit"/>
	            </fieldset>
	        </g:form>
        </div>
    </body>
</html>