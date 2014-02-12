<html>
    <head>
        <meta name="layout" content="new">
        <title>Reset Password</title>
    </head>
    <body>
        <h3>Reset Password</h3>
       	<div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>Please enter your email address so we can look up your user to reset the password.  Once we find it, we will send you an
            	email to reset your password.</p>
            </div>
        </div>
        <div class="dash rounded skinny-scaled spaced">
        	<span class="legend">User Lookup</span>
        	<div class="inner-x">
		        <g:form>
		            <table class="pairs">
		               <tr><td>Email Address</td><td><g:textField class="email" name="emailAddress" placeHolder="user@domain.com" /></td></tr>
		            </table>
			        <g:submitButton name="submit" value="Continue" />
		        </g:form>
	        </div>
        </div>
    </body>
</html>