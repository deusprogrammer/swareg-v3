<html>
    <body>
        <h3>Merchant Info</h3>
        <g:form>
            <table>
                <tr><td>Pay Pal Email Address</td><td><g:textField name="merchantEmail" value="${eventData?.merchantEmail}"/></td></tr>
                <tr><td>Currency</td><td><g:select name="currency" from="${com.swag.registration.domain.Currency?.values()}" keys="${com.swag.registration.domain.Currency.values()*.name()}" required="" value="${eventInstance?.currency?.name()}"/></td></tr>
                <tr><td>Tax Rate</td><td><g:textField name="taxRate" value="${eventData?.taxRate}"/></td></tr>
            </table>
            <g:submitButton name="back" value="Back"/>
            <g:submitButton name="next" value="Next"/>
        </g:form>
    </body>
</html>