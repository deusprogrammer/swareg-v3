<html>
    <body>
        <h3>Please Enter PayPal API keys</h3>
        <p>This system uses PayPal to collect payments from users creating events and acts as a middle man for collecting payments for registrations for said events.  Please create a developer account at <a href="http://developer.paypal.com" target="_blank">Pay Pal</a>.</p>
        <p>Once you are done, please enter the clientId and secret in the fields below.</p>
        <g:form>
            <table>
                <tr><td>ClientId</td><td><g:textField name="clientId" /></td></tr>
                <tr><td>Secret</td><td><g:textField name="secret" /></td></tr>
            </table>
            <g:submitButton name="next" value="Next"/>
        </g:form>
    </body>
</html>