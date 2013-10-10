<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Login</title>
    </head>
    <body>
        <h3>We found an existing user with that email!</h3>
        <p>Would you like to login using that existing user?  If you have ever registered for or created an event with us before, you have received an email with a temporary password and a link to change your password.  If you never got this email, feel free to click below and we can resend this email.</p>
        <p>If you have your password however, enter it below!</p>
        <g:if test="${message}">
            <div class="message" role="status">${message}</div>
        </g:if>
        <g:form>
            <table>
            <tr><td>Password</td><td><g:passwordField name="password" /></td></tr>
            </table>
            <g:submitButton name="login" value="Login" />
            <g:submitButton name="resend" value="Resend Email" />
        </g:form>
    </body>
</html>