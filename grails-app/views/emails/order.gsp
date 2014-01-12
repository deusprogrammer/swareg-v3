<html>
    <head>
        <meta name="layout" content="email">
    </head>
    <body>
        <h3>Order Confirmation</h3>
        <div class="pretext">
            <p>Thank you for your purchase!  Below is your receipt.  Your receipt number is what you will give us should you have
            any problems with your purchase.  All you need to get your badge on the day of the event is your email address
            and your ID.  You can also go to your dashboard on SWAreG and print out your pre reg form to speed things up.</p>
            <p>If you bought more than one registration to this convention, you can gift them to other users through your
            dashboard as well!  Just click and hold on a badge, select gift and then enter their email address!  That easy!</p>
        </div>
        <div class="round">
			<fieldset class="flow">
		      <legend>Receipt</legend>
		      <table class="b">
		          <tr><td>Receipt Number</td><td>${order?.paymentId}</td></tr>
		          <tr><td>Payment Type</td><td>${order?.paymentType}</td></tr>
		          <tr>
			          <td colspan="2">
			          	 <center>Badges</center>
			          	 <hr>
			          	 <table>
			          	 	 <tr><th>Badge Level</th><th>Quantity</th><th>Price</th><th>Total</th></tr>
						     <g:each in="${order.badges}" var="badge">
							 	<tr><td>${badge.registrationLevel}</td><td align="right">${badge.quantity}</td><td align="right">${String.format("%.2f", badge.getPrice())} ${order?.getCurrency().getCurrencyCode()}</td><td align="right">${String.format("%.2f", badge.getPrice() * badge.quantity)} ${order?.getCurrency().getCurrencyCode()}</td></tr>
						     </g:each>
						     <tr><td></td><td><b>Subtotal</b></td><td></td><td align="right">${String.format("%.2f", order?.getSubtotal())} ${order?.getCurrency().getCurrencyCode()}</td></tr>
							 <tr><td></td><td><b>Tax</b></td><td></td><td align="right">${String.format("%.2f", order?.getTax())} ${order?.getCurrency().getCurrencyCode()}</td></tr>
							 <tr><td></td><td><b>Total</b></td><td></td><td align="right">${String.format("%.2f", order?.getTotal())} ${order?.getCurrency().getCurrencyCode()}</td></tr>
					     </table>
				     </td>
				</tr>
		     </table>
		     </fieldset>
		</div>
    </body>
</html>