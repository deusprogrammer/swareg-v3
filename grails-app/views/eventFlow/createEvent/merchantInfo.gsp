<html>
    <head>
        <meta name="layout" content="flow">
        <title>Event:Merchant-Info</title>
    </head>
    <body>
        <h3>Merchant Info</h3>
        <div class="pretext">
            <p>Next up, lets file in your merchant info.  Give us the email address you use for PayPal, and what your local currency and sales tax is.</p>
        </div>
        <div class="round">
	        <g:form>
	            <table>
	                <tr><td>Pay Pal Email Address</td><td><g:textField name="merchantEmail" value="${eventData?.merchantEmail}"/></td></tr>
	                <tr><td>Currency</td><td><g:select name="currency" from="${com.swag.registration.domain.Currency?.values()}" keys="${com.swag.registration.domain.Currency.values()*.name()}" required="" value="${eventInstance?.currency?.name()}"/></td></tr>
	                <tr><td>Tax Rate</td><td><g:textField name="taxRate" value="${eventData?.taxRate}"/></td></tr>
	            </table>
	            <g:submitButton name="back" value="Back"/>
	            <g:submitButton name="next" value="Next"/>
	        </g:form>
        </div>
    </body>
</html>