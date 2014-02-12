<html>
    <head>
        <meta name="layout" content="flow">
        <title>Password Reset</title>
    </head>
    <body>
        <h3>Enter New Password</h3>
       	<div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>Please enter a new password.</p>
            </div>
        </div>
        <div class="dash rounded skinny-scaled spaced">
        	<span class="legend">Set New Password</span>
        	<div class="inner-x">
	        <g:form>
	            <table class="pairs">
		            <tr><td>Password</td><td><g:passwordField name="password1" class="password" value="${userData?.password}" /></td></tr>
		            <tr><td></td><td><g:passwordField name="password2" class="password-confirm" value="${userData?.password}" /></td></tr>
	            </table>
	            <g:submitButton name="submit" value="Submit"/>
	        </g:form>
	        </div>
        </div>
    </body>
</html>