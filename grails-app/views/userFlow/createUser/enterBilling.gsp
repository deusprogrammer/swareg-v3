<html>
    <body>
        <h3>Billing Data</h3>
        <g:form>
            <table>
                <tr><td>*Same as Shipping</td><td><g:checkBox name="sameAsShipping" value="${flow?.billingData?.sameAsShipping}"/></td></tr>
                <tr><td>Street Address</td><td><g:textField name="streetAddress1" value="${flow?.billingData?.streetAddress1}"/></td></tr>
                <tr><td></td><td><g:textField name="streetAddress2" value="${flow?.billingData?.streetAddress2}"/></td></tr>
                <tr><td>City</td><td><g:textField name="city" value="${flow?.billingData?.city}"/></td></tr>
                <tr><td>State</td><td><g:textField name="state" value="${flow?.billingData?.state}"/></td></tr>
                <tr><td>Zip Code</td><td><g:textField name="zipCode" value="${flow?.billingData?.zipCode}"/></td></tr>
            </table>
            <g:submitButton name="back" value="Back"/>
            <g:submitButton name="next" value="Next"/>
        </g:form>
    </body>
</html>