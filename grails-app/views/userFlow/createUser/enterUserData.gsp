<html>
    <body>
        <h3>User Information</h3>
        <g:form>
            <table>
	            <tr><td>Username</td><td><g:textField name="username" value="${userData?.username}"/></td></tr>
	            <tr><td>Password</td><td><g:textField name="password" value="${userData?.password}"/></td></tr>
	            <tr><td>Email</td><td><g:textField name="emailAddress" value="${userData?.emailAddress}"/></td></tr>
	            <tr><td>First Name</td><td><g:textField name="firstName" value="${userData?.firstName}"/></td></tr>
	            <tr><td>Last Name</td><td><g:textField name="lastName" value="${userData?.lastName}"/></td></tr>
	            <tr><td>Gender</td><td><g:textField name="gender" value="${userData?.gender}"/></td></tr>
	            <tr><td>Age</td><td><g:field name="age" type="number" value="${userData?.age}"/></td></tr>
            </table>
            <g:submitButton name="next" value="Next"/>
        </g:form>
    </body>
</html>