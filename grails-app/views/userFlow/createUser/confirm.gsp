<html>
    <head>
        <meta name="layout" content="flow">
        <title>User:Confirm</title>
    </head>
    <body>
        <h3>Confirm</h3>
        <div class="pretext">
            <p>Now just look over your data and confirm that it is correct.</p>
        </div>
        <g:form>
            <div class="round">
                <fieldset class="flow">
	                <legend>User Info</legend>
		            <table>
	                    <tr><td>Email</td><td>${userData?.emailAddress}</td></tr>
		                <tr><td>Password</td><td>****************</td></tr>
		                <tr><td>First Name</td><td>${userData?.firstName}</td></tr>
		                <tr><td>Last Name</td><td>${userData?.lastName}</td></tr>
		            </table>
	            </fieldset>
            </div>
            <g:if test="${!isRegistration}">
	            <div class="round">
	                <fieldset class="flow">
		                <legend>Shipping Address</legend>
			            <table>
			                <tr><td>Street Address</td><td>${shippingData?.streetAddress1}</td></tr>
			                <tr><td></td><td>${shippingData?.streetAddress2}</td></tr>
			                <tr><td>City</td><td>${shippingData?.city}</td></tr>
			                <tr><td>State</td><td>${shippingData?.state}</td></tr>
			                <tr><td>Zip Code</td><td>${shippingData?.zipCode}</td></tr>
		            </table>
		            </fieldset>
		            <fieldset class="flow">
			            <legend>Billing Address</legend>
			            <table>
			                <tr><td>Street Address</td><td>${billingData?.streetAddress1}</td></tr>
			                <tr><td></td><td>${billingData?.streetAddress2}</td></tr>
			                <tr><td>City</td><td>${billingData?.city}</td></tr>
			                <tr><td>State</td><td>${billingData?.state}</td></tr>
			                <tr><td>Zip Code</td><td>${billingData?.zipCode}</td></tr>
			            </table>
		            </fieldset>
	            </div>
              	<div class="round">
            		<fieldset class="flow">
            			<legend>Captcha</legend>
            			<img src="${createLink(controller: 'simpleCaptcha', action: 'captcha')}"/><br/>
						<label for="captcha">Type the letters above in the box below:</label><br/>
						<g:textField name="captcha"/>
            		</fieldset>
            	</div>
            </g:if>
            <br/>
            <div class="round">
	            <g:submitButton name="back" value="Back"/>
				<g:submitButton name="confirm" value="Confirm"/>
			</div>
        </g:form>
    </body>
</html>