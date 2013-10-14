<html>
    <head>
        <meta name="layout" content="flow">
        <title>Event:Confirm</title>
    </head>
    <body>
        <h3>Confirm</h3>
        <div class="pretext">
            <p>Now just look over your data and confirm that it is correct.</p>
        </div>
        <g:form>
           <div class="round">
	            <h4>Basic Info</h4>
	            <g:submitButton name="editBasicInfo" value="Edit" />
	            <table>
	                <tr><td>Name</td><td>${eventData?.name}</td></tr>
	                <tr><td>Year</td><td>${eventData?.year}</td></tr>
	                <tr><td>Numeral</td><td>${eventData?.numeral}</td></tr>
	                <tr><td>Home Page</td><td>${eventData?.homePage}</td></tr>
	            </table>
            </div>
            <div class="round">
            <h4>Merchant Info</h4>
	            <g:submitButton name="editMerchantInfo" value="Edit" />
	            <table>
	                <tr><td>Pay Pal Email Address</td><td>${eventData?.merchantEmail}</td></tr>
	                <tr><td>Currency</td><td>${eventData?.currency}</td></tr>
	                <tr><td>Tax Rate</td><td>${eventData?.taxRate}</td></tr>
	            </table>
            </div>
            <div class="round">
	            <h4>Registration Levels</h4>
	            <g:submitButton name="editRegistrationInfo" value="Edit" />
	            <table>
	                <g:each in="${eventData?.registrationLevels}" var="level">
	                    <tr><td>${level.name}</td><td>${level.price}</td></tr>
	                </g:each>
	            </table>
            </div>
            <br/>
            <div class="round">
	            <g:submitButton name="back" value="Back"/>
				<g:submitButton name="confirm" value="Confirm"/>
			</div>
        </g:form>
    </body>
</html>