<html>
    <head>
        <meta name="layout" content="flow">
        <title>Event:Basic-Info</title>
    </head>
    <body>
        <h3>Event Info</h3>
        <div class="pretext">
            <p>Okay, lets get started creating your event.  First lets get some basic info.</p>
        </div>
        <div class="round">
	        <g:form>
	            <table>
	                <tr><td>Event Name</td><td><g:textField name="name" value="${eventData?.name}"/></td></tr>
	                <tr><td>Year</td><td><g:textField name="year" value="${eventData?.year}"/></td></tr>
	                <tr><td>Numeral</td><td><g:textField name="numeral" value="${eventData?.numeral}"/></td></tr>
	                <tr><td>Home Page</td><td><g:textField name="homePage" value="${eventData?.homePage}"/></td></tr>
	            </table>
	            <g:submitButton name="next" value="Next"/>
	        </g:form>
        </div>
    </body>
</html>