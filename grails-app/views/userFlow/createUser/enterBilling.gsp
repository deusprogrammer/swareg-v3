<html>
    <head>
        <meta name="layout" content="flow">
        <title>User:Billing</title>
    </head>
    <body>
        <h3>Billing Data</h3>
        <div class="pretext">
            <p>Please enter your billing address.</p>
        </div>
        <div class="round">
	        <g:form>
	            <fieldset class="flow">
	                <legend>Billing Information</legend>
		            <table>
		                <tr><td>*Same as Shipping</td><td><g:checkBox name="sameAsShipping" id="sameAsShipping" value="${flow?.billingData?.sameAsShipping}"/></td></tr>
		                <tr><td>Street Address</td><td><g:textField name="streetAddress1" class="disablable" value="${flow?.billingData?.streetAddress1}"/></td></tr>
		                <tr><td></td><td><g:textField name="streetAddress2" class="disablable" value="${flow?.billingData?.streetAddress2}"/></td></tr>
		                <tr><td>City</td><td><g:textField name="city" class="disablable" value="${flow?.billingData?.city}"/></td></tr>
		                <tr><td>State</td><td><g:textField name="state" class="disablable" value="${flow?.billingData?.state}"/></td></tr>
		                <tr><td>Zip Code</td><td><g:textField name="zipCode" class="disablable" value="${flow?.billingData?.zipCode}"/></td></tr>
		            </table>
		            <g:submitButton name="back" value="Back"/>
		            <g:submitButton name="next" value="Next"/>
	            </fieldset>
	        </g:form>
        </div>
    </body>
</html>