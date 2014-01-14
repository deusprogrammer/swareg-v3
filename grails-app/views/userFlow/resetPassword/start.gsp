<html>
    <head>
        <meta name="layout" content="flow">
        <title>Reset Password</title>
    </head>
    <body>
        <h3>Reset Password</h3>
        <div class="pretext">
            <p>Please enter your email address so we can look up your user to reset the password.  Once we find it, we will send you an
            email to reset your password.</p>
        </div>
        <div class="round">
	        <g:form>
                <fieldset class="flow">
                    <legend>User Lookup</legend>
		            <table>
		               <tr><td>Email Address</td><td><g:textField class="email" name="emailAddress" placeHolder="user@domain.com" /></td></tr>
		            </table>
			        <g:submitButton name="submit" value="Continue" />
		        </fieldset>
	        </g:form>
        </div>
    </body>
</html>