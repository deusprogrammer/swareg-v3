<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Finish</title>
    </head>
    <body>
        <h3>Thank You!</h3>
        <div class="pretext">
            <g:if test="${order && order.paymentCompleted}">
                <p>Thank you for your purchase.  Your receipt number is ${order.paymentId}.  You will be receiving an email from us soon with a copy of this receipt and your account information.</p>
            </g:if>
            <g:else>
                <p>Unable to process your payment!  Please try again later!  Your account has not been charged.</p>
            </g:else>
        </div>
        <div class="round">
	        <g:form>
	            <fieldset class="flow">
		            <legend>Confirm</legend>
		            <g:submitButton name="confirm" value="Go Home"/>
	            </fieldset>
	        </g:form>
        </div>
    </body>
</html>