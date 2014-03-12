<html>
    <head>
        <meta name="layout" content="flow">
        <title>Event:Confirm</title>
    </head>
    <body>
        <h3>Confirm</h3>
        <div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>Now just look over your data and confirm that it is correct.</p>
          	</div>
        </div>
        <g:form>
        	<div class="spaced">
		        <div class="dash rounded skinny-scaled shortest side-by-side top">
		       		<span class="legend">Basic Info</span>
		       		<div class="inner">
			            <table>
			                <tr><td>Name</td><td>${eventData?.name}</td></tr>
			                <tr><td>Year</td><td>${eventData?.year}</td></tr>
			                <tr><td>Numeral</td><td>${eventData?.numeral}</td></tr>
			                <tr><td>Home Page</td><td>${eventData?.homePage}</td></tr>
			            </table>
		            </div>
		        </div>
		        <div class="dash rounded skinny-scaled shortest side-by-side top">
		        	<span class="legend">Merchant Info</span>
		        	<div class="inner">
			            <table>
			                <tr><td>Pay Pal Email Address</td><td>${eventData?.merchantEmail}</td></tr>
			                <tr><td>Currency</td><td>${eventData?.currency}</td></tr>
			                <tr><td>Tax Rate</td><td>${eventData?.taxRate}</td></tr>
			            </table>
					</div>
				</div>
			</div>
	        <div class="dash rounded skinny-scaled spaced">
	        	<span class="legend">Captcha</span>
	        	<div class="inner-x">
					<img src="${createLink(controller: 'simpleCaptcha', action: 'captcha')}"/><br/>
					<label for="captcha">Type the letters above in the box below:</label><br/>
					<g:textField name="captcha"/>
					<g:submitButton name="back" value="Back"/>
					<g:submitButton name="confirm" value="Confirm"/>
				</div>
			</div>
        </g:form>
    </body>
</html>