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
	            <fieldset class="flow">
	                <legend>Basic Info</legend>
		            <g:submitButton name="editBasicInfo" value="Edit" />
		            <table>
		                <tr><td>Name</td><td>${eventData?.name}</td></tr>
		                <tr><td>Year</td><td>${eventData?.year}</td></tr>
		                <tr><td>Numeral</td><td>${eventData?.numeral}</td></tr>
		                <tr><td>Home Page</td><td>${eventData?.homePage}</td></tr>
		            </table>
	            </fieldset>
                <fieldset class="flow">
                    <legend>Merchant Info</legend>
		            <g:submitButton name="editMerchantInfo" value="Edit" />
		            <table>
		                <tr><td>Pay Pal Email Address</td><td>${eventData?.merchantEmail}</td></tr>
		                <tr><td>Currency</td><td>${eventData?.currency}</td></tr>
		                <tr><td>Tax Rate</td><td>${eventData?.taxRate}</td></tr>
		            </table>
	            </fieldset>
            </div>
            <div class="round">
                <fieldset class="flow">
                    <legend>Registration Levels</legend>
		            <g:submitButton name="editRegistrationInfo" value="Edit" />
		            <table>
		                <g:each in="${eventData?.registrationLevels}" var="level">
		                    <tr><td>${level?.name}</td><td>${String.format("%.2f",level?.price)} ${eventData?.currency}</td></tr>
		                </g:each>
		            </table>
	            </fieldset>
            </div>
            <br/>
            <div class="round">
	            <g:submitButton name="back" value="Back"/>
				<g:submitButton name="confirm" value="Confirm"/>
			</div>
        </g:form>
    </body>
</html>