<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Complete</title>
    </head>
    <body>
        <h3>Thank you for Registering!</h3>
        <div class="round">
            <g:if test="${payment && payment.completed}">
                <p>Thank you for your purchase.  Your receipt number is ${payment.paymentId}.  You will be receiving an email from us soon with a copy of this receipt and your account information.</p>
            </g:if>
            <g:else>
                <p>Unable to process your payment!  Please try again later!  Your account has not been charged.</p>
            </g:else>
        </div>
    </body>
</html>