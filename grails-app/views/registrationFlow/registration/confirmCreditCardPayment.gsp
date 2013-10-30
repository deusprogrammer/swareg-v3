<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Confirmation</title>
    </head>
    <body>
        <h3>Please Confirm Your Purchase</h3>
        <div class="round">
	        <p>You wish to purchase a ${registration.registrationLevel.name} registration for ${event.name} and charge ${String.format("\$%.2f", registration.registrationLevel.currentPrice * (1.0 + event.taxRate))} to your
	        ${ccData.type} ending in ${ccData.creditCardNumber[-4..-1].toString()}?</p>
	        <g:form>
	            <g:submitButton name="confirm" value="Confirm"/>
	            <g:submitButton name="cancel" value="Cancel"/>
	        </g:form>
        </div>
    </body>
</html>