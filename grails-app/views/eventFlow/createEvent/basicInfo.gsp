<html>
    <head>
        <meta name="layout" content="flow">
        <title>Event:Basic-Info</title>
    </head>
    <body>
        <h3>Event Info</h3>
        <div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>Okay, lets get started creating your event.  First lets get some basic info.</p>
            </div>
        </div>
        <div class="dash rounded skinny-scaled spaced">
        	<span class="legend">Basic Info</span>
        	<div class="inner-x">
		        <g:form>
		            <table class="pairs">
		                <tr><td>Event Name</td><td><g:textField name="name" value="${eventData?.name}" placeholder="Convention Name" /></td></tr>
		                <tr><td>Year</td><td><g:textField name="year" class="numeric" value="${eventData?.year}" placeholder="2014"/></td></tr>
		                <tr><td>Numeral</td><td><g:textField name="numeral" class="numeric" value="${eventData?.numeral}" placeholder="1" /></td></tr>
		                <tr><td>Home Page</td><td><g:textField name="homePage" class="url" value="${eventData?.homePage}" placeholder="http://hostname.com"/></td></tr>
		            </table>
		            <g:submitButton name="next" value="Next"/>
		        </g:form>
	        </div>
        </div>
    </body>
</html>