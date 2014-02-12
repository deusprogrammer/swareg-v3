<html>
    <head>
        <meta name="layout" content="new">
        <title>Event:Login</title>
    </head>
    <body>
        <h3>Login</h3>
        <div class="dash skinny-scaled shortest side-by-side top">
       		<div class="inner">
	            <p>In order to create an event on SWAreG, you must have a user and be logged in.</p>
	            <p>Please login below or create a new user</p>
	        </div>
        </div>
        <div class="dash rounded skinny-scaled shorter side-by-side top">
       		<span class="legend">Login</span>
       		<div class="inner">
		        <g:form>
		            <table>
		            <tr><td>Email Address</td><td><g:textField name="emailAddress" /></td></tr>
		            <tr><td>Password</td><td><g:passwordField name="password" /></td></tr>
		            </table>
		            <g:submitButton name="login" value="Login" />
		            <g:submitButton name="createUser" value="New User" />
		        </g:form>
	        </div>
       	</div>
    </body>
</html>