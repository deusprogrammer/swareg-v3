<html>
    <head>
        <meta name="layout" content="flow">
        <title>Setup:Gmail</title>
    </head>
    <body>
        <h3>Please Enter GMail Account Info</h3>
   		<div class="dash small-scaled shortest spaced">
       		<div class="inner">
		        <p>This system uses your GMail account to send out confirmations and information to your customers.
	        	Your information will be encrypted and stored in our database using Blowfish.</p>
	        </div>
        </div>
        <div class="dash rounded small-scaled spaced">
       		<span class="legend">Email Settings</span>
       		<div class="inner-x">
       			<g:form>
		            <table>
		                <tr><td>GMail Address</td><td><g:textField size="65" name="emailAddress" /></td></tr>
		                <tr><td>GMail Password</td><td><g:textField size="65" name="emailPassword" /></td></tr>
		            </table>
		            <g:submitButton name="next" value="Next"/>
	            </g:form>
	        </div>
       	</div>
    </body>
</html>