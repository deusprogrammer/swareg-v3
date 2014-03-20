<html>
    <head>
        <meta name="layout" content="flow">
        <title>User:Confirm</title>
    </head>
    <body>
        <h3>Confirm</h3>
        <div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>Now just look over your data and confirm that it is correct.</p>
            </div>
        </div>
        <g:form>
            <div class="dash rounded skinny-scaled spaced">
		       	<span class="legend">User Info</span>
		       	<div class="inner-x">
		            <table class="pairs">
	                    <tr><td>Email</td><td>${userData?.emailAddress}</td></tr>
		                <tr><td>Password</td><td>****************</td></tr>
		                <tr><td>First Name</td><td>${userData?.firstName}</td></tr>
		                <tr><td>Last Name</td><td>${userData?.lastName}</td></tr>
		            </table>
	            </div>
            </div>
            <g:if test="${!isRegistration}">
	            <div class="spaced">
					<div class="dash rounded skinny-scaled side-by-side top">
				    	<span class="legend">Shipping Address</span>
				       	<div class="inner-x">
				            <table class="pairs">
				                <tr><td>Street Address</td><td>${shippingData?.streetAddress1}</td></tr>
				                <tr><td></td><td>${shippingData?.streetAddress2}</td></tr>
				                <tr><td>City</td><td>${shippingData?.city}</td></tr>
				                <tr><td>State</td><td>${shippingData?.state}</td></tr>
				                <tr><td>Zip Code</td><td>${shippingData?.zipCode}</td></tr>
				            </table>
			            </div>
					</div>
		        </div>
            </g:if>
			<div class="dash rounded skinny-scaled spaced">
	       		<span class="legend">Captcha</span>
	       		<div class="inner-x">
           			<img src="${createLink(controller: 'simpleCaptcha', action: 'captcha')}"/><br/>
					<label for="captcha">Type the letters above in the box below:</label><br/>
					<g:textField name="captcha"/>
					<g:submitButton name="back" value="Back"/>
					<g:submitButton name="confirm" value="Confirm"/>
           		</div>
           	</div>
        </g:form>
    </body>
</html>