<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Complete</title>
    </head>
    <body>
        <h3>Confirming Purchase</h3>
        <div class="pretext">
            <p>Are you sure you want to complete your purchase of ${String.format("%.2f",order.getTotal())} ${order.getCurrency().getCurrencyCode()} for ${order.event}?</p>
        </div>
        <div class="round">
	        <g:form>
	            <fieldset class="flow">
		            <legend>Confirm</legend>
		            <g:submitButton name="confirm" value="Confirm"/>
		            <g:submitButton name="cancel" value="Cancel"/>
	            </fieldset>
	        </g:form>
        </div>
    </body>
</html>