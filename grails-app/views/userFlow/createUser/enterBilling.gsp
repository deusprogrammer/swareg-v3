<html>
    <head>
        <meta name="layout" content="new">
        <title>User:Billing</title>
    </head>
    <body>
        <h3>Billing Data</h3>
		<div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>Please enter your shipping address.</p>
            </div>
        </div>
        <div class="dash rounded skinny-scaled spaced">
        	<span class="legend">Billing Address</span>
        	<div class="inner-x">
		        <g:form>
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
		        </g:form>
	        </div>
        </div>
    </body>
</html>