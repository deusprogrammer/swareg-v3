<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Confirmation</title>
    </head>
    <body>
        <h3>Please Confirm Your Purchase</h3>
        <div class="round">
            <p>You wish to approve your PayPal payment of ${payment.total}?</p>
            <g:form>
                <g:submitButton name="confirm" value="Confirm"/>
                <g:submitButton name="cancel" value="Cancel"/>
            </g:form>
        </div>
    </body>
</html>