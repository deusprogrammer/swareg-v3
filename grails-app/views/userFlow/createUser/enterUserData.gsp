<html>
    <head>
        <meta name="layout" content="flow">
        <title>User:Data</title>
    </head>
    <body>
        <h3>User Information</h3>
        <div class="pretext">
            <p>Let's begin.  Please enter your user information.  All information is required.</p>
        </div>
        <div class="round">
	        <g:form>
	            <fieldset class="flow">
		            <legend>User Data</legend>
		            <table>
		                <g:if test="${isRegistration}">
			                 <tr><td>Email</td><td><g:textField class="email" name="emailAddress" value="${userData?.emailAddress}" readonly="readonly" /></td></tr>
			            </g:if>
			            <g:else>
			                 <tr><td>Email</td><td><g:textField class="email" name="emailAddress" value="${userData?.emailAddress}" /></td></tr>
			            </g:else>
			            <tr><td>Password</td><td><g:passwordField name="password1" class="password" value="${userData?.password}" /></td></tr>
			            <tr><td></td><td><g:passwordField name="password2" class="password-confirm" value="${userData?.password}" /></td></tr>
			            <tr><td>First Name</td><td><g:textField name="firstName" value="${userData?.firstName}"/></td></tr>
			            <tr><td>Last Name</td><td><g:textField name="lastName" value="${userData?.lastName}"/></td></tr>
		            </table>
		            <g:submitButton name="next" value="Next"/>
	            </fieldset>
	        </g:form>
        </div>
    </body>
</html>