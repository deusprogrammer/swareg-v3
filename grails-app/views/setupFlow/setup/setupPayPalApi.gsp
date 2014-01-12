<html>
    <head>
        <meta name="layout" content="flow">
        <title>Setup:PayPal</title>
    </head>
    <body>
        <h3>Please Enter PayPal API keys</h3>
        <div class="pretext">
	        <p>This system uses PayPal to collect payments from users creating events and acts as a middle man for collecting payments for registrations for said events.  Please create a developer account at <a href="http://developer.paypal.com" target="_blank">Pay Pal</a>.</p>
	        <p>Once you are done, please enter the clientId and secret in the fields below.</p>
        </div>
        
        <div class="round">
	        <g:form>
                <fieldset class="flow">	                    
                    <legend>PayPal API Settings</legend>
		            <table>
		                <tr><td>ClientId</td><td><g:textField size="80" name="clientId" /></td></tr>
		                <tr><td>Secret</td><td><g:textField size="80" name="secret" /></td></tr>
		            </table>
		            <g:submitButton name="next" value="Next"/>
	            </fieldset>
	        </g:form>
        </div>
    </body>
</html>