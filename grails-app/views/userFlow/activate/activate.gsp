<html>
    <head>
        <meta name="layout" content="flow">
        <title>User:Activation</title>
    </head>
    <body>
        <h3>User Activation</h3>
        <div class="pretext">
            <p>You have either registered at a convention using an email address, offered a position by a convention, or you were gifted a badge by another user.  Let's set your account.</p>
            <p>Once you have activated your account, you can accept any gifted registrations and update your user information if you want.  Giving us more information isn't required, but it assists us in mailing out badges if the convention you are attending ships them out early.</p>
        </div>
        <div class="round">
	        <g:form>
	            <fieldset class="flow">
		            <legend>Set Password</legend>
		            <table>
			            <tr><td>Password</td><td><g:passwordField name="password1" class="password" /></td></tr>
			            <tr><td></td><td><g:passwordField name="password2" class="password-confirm" /></td></tr>
			            <tr><td>First Name</td><td><g:textField name="firstName" /></td></tr>
			            <tr><td>Last Name</td><td><g:textField name="lastName" /></td></tr>
		            </table>
		            <g:submitButton name="next" value="Next"/>
	            </fieldset>
	        </g:form>
        </div>
    </body>
</html>