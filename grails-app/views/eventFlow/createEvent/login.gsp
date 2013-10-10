<html>
    <body>
        <h3>Login</h3>
        <p>In order to create an event on SWAreG, you must have a user and be logged in.</p>
        <p>Please login below or create a new user</p>
        <g:form>
            <table>
            <tr><td>Email Address</td><td><g:textField name="emailAddress" /></td></tr>
            <tr><td>Password</td><td><g:passwordField name="password" /></td></tr>
            </table>
            <g:submitButton name="login" value="Login" />
            <g:submitButton name="createUser" value="New User" />
        </g:form>
    </body>
</html>