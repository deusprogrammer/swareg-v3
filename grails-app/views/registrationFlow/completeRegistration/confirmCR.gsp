<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Complete</title>
    </head>
    <body>
        <h3>Confirming Purchase</h3>
       	<div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>Are you sure you want to complete your purchase of ${String.format("%.2f",order.getTotal())} ${order.getCurrency().getCurrencyCode()} for ${order.event}?</p>
            	<g:form>
		            <g:submitButton name="confirm" value="Confirm"/>
		            <g:submitButton name="cancel" value="Cancel"/>
		        </g:form>
            </div>
        </div>
        
        <div class="pretext">
            
        </div>
        <div class="round">

        </div>
    </body>
</html>