<html>
    <head>
        <meta name="layout" content="flow">
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
		        	<fieldset style="border: none;">
			            <table>
			                <tr><td>*Same as Shipping</td><td><g:checkBox name="sameAsShipping" id="sameAsShipping" class="disableoncheck" value="${flow?.billingData?.sameAsShipping}"/></td></tr>
			                <tr><td>Street Address</td><td><g:textField name="streetAddress1" class="disablable" value="${flow?.billingData?.streetAddress1}" placeholder="1234 Street Name" /></td></tr>
			                <tr><td></td><td><g:textField name="streetAddress2" class="disablable" value="${flow?.billingData?.streetAddress2}" placeholder="Apt 1234" /></td></tr>
			                <tr><td>City</td><td><g:textField name="city" class="disablable" value="${flow?.billingData?.city}"  placeholder="City" /></td></tr>
			                <tr><td>State</td><td><g:textField name="state" class="disablable" value="${flow?.billingData?.state}"  placeholder="State" /></td></tr>
			                <tr><td>Zip Code</td><td><g:textField name="zipCode" class="disablable" value="${flow?.billingData?.zipCode}" placeholder="12345" /></td></tr>
			            </table>
		            </fieldset>
		            <g:submitButton name="back" value="Back"/>
		            <g:submitButton name="next" value="Next"/>
		        </g:form>
	        </div>
        </div>
    </body>
</html>