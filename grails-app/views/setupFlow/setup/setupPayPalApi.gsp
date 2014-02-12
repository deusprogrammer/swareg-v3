<html>
    <head>
        <meta name="layout" content="flow">
        <title>Setup:PayPal</title>
    </head>
    <body>
        <h3>Please Enter PayPal API keys</h3>
   		<div class="dash small-scaled shorter spaced">
       		<div class="inner">
		        <p>This system uses PayPal to collect payments from users creating events and acts as a middle man for collecting payments for registrations for said events.  Please create a developer account at <a href="http://developer.paypal.com" target="_blank">Pay Pal</a>.</p>
	        	<p>Once you are done, please enter the clientId and secret in the fields below.</p>
	        </div>
        </div>
        <div class="dash rounded small-scaled spaced">
       		<span class="legend">Pay Pal API Settings</span>
       		<div class="inner-x">
       			<g:form>
			        <table>
			                <tr><td>ClientId</td><td><g:textField size="80" name="clientId" /></td></tr>
			                <tr><td>Secret</td><td><g:textField size="80" name="secret" /></td></tr>
		            </table>
		            <g:submitButton name="next" value="Next"/>
	            </g:form>
	        </div>
       	</div>
    </body>
</html>