<html>
    <head>
        <meta name="layout" content="flow">
        <title>User:Data</title>
    </head>
    <body>
        <h3>User Information</h3>
        
       	<div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>Let's begin.  Please enter your user information.  All information is required.</p>
            </div>
        </div>
        <div class="dash rounded skinny-scaled spaced">
        	<span class="legend">User Data</span>
        	<div class="inner-x">
				<g:form>
		            <table>
		                <g:if test="${isRegistration}">
			                 <tr><td>Email</td><td><g:textField class="email" name="emailAddress" value="${userData?.emailAddress}" placeholder="user@domain.com" readonly="readonly" /></td></tr>
			            </g:if>
			            <g:else>
			                 <tr><td>Email</td><td><g:textField class="email" name="emailAddress" value="${userData?.emailAddress}" placeholder="user@domain.com" /></td></tr>
			            </g:else>
			            <tr><td>Password</td><td><g:passwordField name="password1" class="password" value="${userData?.password}" /></td></tr>
			            <tr><td></td><td><g:passwordField name="password2" class="password-confirm" value="${userData?.password}" /></td></tr>
			            <tr><td>First Name</td><td><g:textField name="firstName" value="${userData?.firstName}" placeholder="John"/></td></tr>
			            <tr><td>Last Name</td><td><g:textField name="lastName" value="${userData?.lastName}" placeholder="Doe"/></td></tr>
		            </table>
		            <g:submitButton name="next" value="Next"/>
	        	</g:form>
	        </div>
        </div>
    </body>
</html>