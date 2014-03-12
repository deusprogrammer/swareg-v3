<html>
    <head>
        <meta name="layout" content="flow">
        <title>User:Activation</title>
    </head>
    <body>
        <h3>User Activation</h3>
       	<div class="dash small-scaled spaced">
        	<div class="inner-x">
            	<p>You have either registered at a convention using an email address, offered a position by a convention, or you were gifted a badge by another user.  Let's set your account.</p>
            	<p>Once you have activated your account, you can accept any gifted registrations and update your user information if you want.  Giving us more information isn't required, but it assists us in mailing out badges if the convention you are attending ships them out early.</p>
            </div>
        </div>
        <div class="dash rounded skinny-scaled spaced">
        	<span class="legend">Set Information</span>
        	<div class="inner-x">
		        <g:form>
		            <table class="pairs">
			            <tr><td>Password</td><td><g:passwordField name="password1" class="password" /></td></tr>
			            <tr><td></td><td><g:passwordField name="password2" class="password-confirm" /></td></tr>
			            <tr><td>First Name</td><td><g:textField name="firstName" placeholder="John" /></td></tr>
			            <tr><td>Last Name</td><td><g:textField name="lastName" placeholder="Doe" /></td></tr>
		            </table>
		            <g:submitButton name="next" value="Next"/>
		        </g:form>
	        </div>
        </div>
    </body>
</html>