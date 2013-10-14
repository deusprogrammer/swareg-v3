<html>
    <body>
        <g:form>
            <div class="round">
	            <h4>User Data</h4>
	            <g:actionSubmit name="editUserInfo" value="Edit" />
	            <table>
	                <tr><td>Username</td><td>${userData?.username}</td></tr>
	                <tr><td>Password</td><td>${userData?.password}</td></tr>
	                <tr><td>Email</td><td>${userData?.emailAddress}</td></tr>
	                <tr><td>First Name</td><td>${userData?.firstName}</td></tr>
	                <tr><td>Last Name</td><td>${userData?.lastName}</td></tr>
	                <tr><td>Gender</td><td>${userData?.gender}</td></tr>
	                <tr><td>Age</td><td>${userData?.age}</td></tr>
	            </table>
            </div>
            <div class="round">
	            <h4>Shipping Info</h4>
	            <g:actionSubmit name="editShippingInfo" value="Edit" />
	            <table>
	                <tr><td>Street Address</td><td>${shippingData?.streetAddress1}</td></tr>
	                <tr><td></td><td>${shippingData?.streetAddress2}</td></tr>
	                <tr><td>City</td><td>${shippingData?.city}</td></tr>
	                <tr><td>State</td><td>${shippingData?.state}</td></tr>
	                <tr><td>Zip Code</td><td>${shippingData?.zipCode}</td></tr>
	            </table>
	        </div>
	        <div class="round">
	            <h4>Billing Info</h4>
	            <g:actionSubmit name="editBillingInfo" value="Edit" />
	            <table>
	                <tr><td>Street Address</td><td>${billingData?.streetAddress1}</td></tr>
	                <tr><td></td><td>${billingData?.streetAddress2}</td></tr>
	                <tr><td>City</td><td>${billingData?.city}</td></tr>
	                <tr><td>State</td><td>${billingData?.state}</td></tr>
	                <tr><td>Zip Code</td><td>${billingData?.zipCode}</td></tr>
	            </table>
            </div>
            <div class="round">
	            <g:submitButton name="back" value="Back"/>
				<g:submitButton name="confirm" value="Confirm"/>
			</div>
        </g:form>
    </body>
</html>