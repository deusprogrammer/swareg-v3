<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Login</title>
    </head>
    <body>
        <h3>Register for ${event}</h3>
        <div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>Please enter your email address.  If you have registered using our system previously, you can login and avoid creating a new user.</p>
            </div>
        </div>
        <div class="dash rounded skinny-scaled spaced">
        	<span class="legend">Email Check</span>
        	<div class="inner-x">
		        <g:form>
		            <table class="pairs">
		               <tr><td>Email Address</td><td><g:textField class="email" name="emailAddress" placeHolder="user@domain.com" /></td></tr>
		            </table>
			        <g:submitButton name="continue" value="Continue" />
		        </g:form>
	        </div>
        </div>
    </body>
</html>