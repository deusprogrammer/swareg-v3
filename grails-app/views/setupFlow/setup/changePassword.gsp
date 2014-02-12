<html>
    <head>
        <meta name="layout" content="new">
        <title>Setup:Password</title>
    </head>
    <body>
        <h3>Please Enter a Root Password</h3>
		<div class="dash skinny-scaled shortest spaced">
       		<div class="inner">
		        <p>This system uses a root user named "global_admin" to act as a user that can read/write/delete anything.</p>
		        <p>Please set the password for this user.</p>
	        </div>
        </div>
        <div class="dash rounded skinny-scaled spaced">
       		<span class="legend">Change Password</span>
       		<div class="inner-x">
		        <g:form>
		            <table class="pairs">
		                <tr><td>Password</td><td><g:passwordField name="password" id="password1" /></td></tr>
		                <tr><td></td><td><g:passwordField name="confirmPassword" id="password2" /></td></tr>
		            </table>
		            <g:submitButton name="set" value="Set"/>
		        </g:form>
	        </div>
       	</div>
    </body>
</html>