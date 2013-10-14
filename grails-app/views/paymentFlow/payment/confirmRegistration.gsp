<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Level</title>
    </head>
    <body>
        <h3>Please Confirm Your Purchase</h3>
        <g:if test="${message}">
            <div class="message" role="status">${message}</div>
        </g:if>
        <div class="round">
	        <p>You wish to purchase a ${regLevel.name} registration for ${event.name} and charge ${String.format("\$%.2f", regLevel.currentPrice * (1.0 + event.taxRate))} to your
	        ${ccData.type} ending in ${ccData.creditCardNumber[-4..-1].toString()}?</p>
	        <g:form>
	            <g:submitButton name="confirm" value="Confirm"/>
	        </g:form>
        </div>
    </body>
</html>