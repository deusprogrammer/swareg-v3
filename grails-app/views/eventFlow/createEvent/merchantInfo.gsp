<html>
    <head>
        <meta name="layout" content="new">
        <title>Event:Merchant-Info</title>
    </head>
    <body>
        <h3>Merchant Info</h3>
        <div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>Next up, lets file in your merchant info.  Give us the email address you use for PayPal, and what your local currency and sales tax is.</p>
            </div>
        </div>
        <div class="dash rounded skinny-scaled spaced">
        	<span class="legend">Merchant Info</span>
        	<div class="inner-x">
		        <g:form>
		            <table class="pairs">
		                <tr><td>Pay Pal Email Address</td><td><g:textField class="email" name="merchantEmail" value="${eventData?.merchantEmail}" placeholder="user@email.com" /></td></tr>
		                <tr><td>Currency</td><td><g:select name="currency" from="${com.swag.registration.domain.Currency?.values()}" keys="${com.swag.registration.domain.Currency.values()*.name()}" required="" value="${eventInstance?.currency?.name()}"/></td></tr>
		                <tr><td>Tax Rate</td><td><g:textField class="taxRate" name="taxRate" value="${eventData?.taxRate}" placeholder="0.0123" /></td></tr>
		            </table>
		            <g:submitButton name="back" value="Back"/>
		            <g:submitButton name="next" value="Next"/>
		        </g:form>
	        </div>
        </div>
    </body>
</html>