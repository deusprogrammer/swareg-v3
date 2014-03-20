<html>
    <head>
        <meta name="layout" content="flow">
        <title>User:Shipping</title>
    </head>
    <body>
        <h3>Shipping Data</h3>
		<div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>Please enter your shipping address.</p>
            </div>
        </div>
        <div class="dash rounded skinny-scaled spaced">
        	<span class="legend">Shipping Address</span>
        	<div class="inner-x">
		        <g:form class="validate-me" model="event">
		            <table class="pairs">
		                <tr><td>Street Address</td><td><g:textField name="streetAddress1" value="${flow?.shippingData?.streetAddress1}" placeholder="1234 Street Name" /></td></tr>
		                <tr><td></td><td><g:textField name="streetAddress2" value="${flow?.shippingData?.streetAddress2}" placeholder="Apt 1234" /></td></tr>
		                <tr><td>City</td><td><g:textField name="city" value="${flow?.shippingData?.city}" placeholder="City" /></td></tr>
		                <tr><td>State</td><td><g:textField name="state" value="${flow?.shippingData?.state}" placeholder="State" /></td></tr>
		                <tr><td>Zip Code</td><td><g:textField name="zipCode" value="${flow?.shippingData?.zipCode}" placeholder="12345" /></td></tr>
		            </table>
		            <g:submitButton name="back" value="Back"/>
                    <g:submitButton name="next" value="Next"/>
		        </g:form>
	        </div>
        </div>
    </body>
</html>