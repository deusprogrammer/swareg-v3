<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Login</title>
    </head>
    <body>
        <h3>Register for ${event}</h3>
        <div class="pretext">
            <p>Please enter your email address.  If you have registered using our system previously, you can login and avoid creating a new user.</p>
        </div>
        <div class="round">
	        <g:form>
	            <table>
	               <tr><td>Email Address</td><td><g:textField name="emailAddress" placeHolder="user@domain.com" /></td></tr>
	            </table>
		        <g:submitButton name="continue" value="Continue" />
	        </g:form>
        </div>
    </body>
</html>