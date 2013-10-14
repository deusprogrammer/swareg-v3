<html>
    <head>
        <meta name="layout" content="flow">
        <title>User:Shipping</title>
    </head>
    <body>
        <h3>Shipping Data</h3>
        <div class="pretext">
            <p>Please enter your shipping address.</p>
        </div>
        <div class="round">
	        <g:form>
	            <table>
	                <tr><td>Street Address</td><td><g:textField name="streetAddress1" value="${flow?.shippingData?.streetAddress1}"/></td></tr>
	                <tr><td></td><td><g:textField name="streetAddress2" value="${flow?.shippingData?.streetAddress2}"/></td></tr>
	                <tr><td>City</td><td><g:textField name="city" value="${flow?.shippingData?.city}"/></td></tr>
	                <tr><td>State</td><td><g:textField name="state" value="${flow?.shippingData?.state}"/></td></tr>
	                <tr><td>Zip Code</td><td><g:textField name="zipCode" value="${flow?.shippingData?.zipCode}"/></td></tr>
	            </table>
	            <g:submitButton name="back" value="Back"/>
	            <g:submitButton name="next" value="Next"/>
	        </g:form>
        </div>
    </body>
</html>