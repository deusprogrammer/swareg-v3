<%@ page import="com.swag.registration.domain.Event" %>
<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Choose-Event</title>
    </head>
    <body>
        <h3>Please Choose a Payment Method</h3>
        <div class="pretext">
            <div class="centered">
                <p>Pay with your PayPal account. (Note: you will be redirected to PayPal to login and confirm your purchase.)</p>
                <g:link event="paypal"><button>Pay Pal</button></g:link>
            </div>
            <div class="centered">
                <p>Pay later.  This will not reserve the current promotional price of ${String.format('$%.2f', registration.price)}, but it will make paying later easier, even onsite at the convention!</p>
                <g:link event="payLater"><button>Pay Later</button></g:link>
            </div>
        </div>
    </body>
</html>