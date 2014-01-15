<html>
    <head>
        <meta name="layout" content="email">
    </head>
    <body>
        <h3>You Received a Gift!</h3>
        <div class="pretext">
            <p>You received a gift registration from ${gift.giver} for ${gift.badge.event}.  
            Login to your dashboard on SWAreG to claim it!  See details about your badge below!
            Please make sure you claim your badge as the gift will expire in 30 days and be returned
            to ${gift.giver}.</p>
        </div>
        <div class="round">
			<fieldset class="flow">
		    	<legend>Badge Info</legend>
		    		<table>
		          		<tr><td>Badge Level</td><td>${gift.badge.registrationLevel.name}</td></tr>
		          		<tr><td>Badge Description</td><td>${gift.badge.registrationLevel.description}</td></tr>
		          		<tr><td>Event</td><td>${gift.badge.event}</td></tr>
	          		</table>
		     </fieldset>
	    </div>
    </body>
</html>