<html>
    <head>
        <meta name="layout" content="email">
    </head>
    <body>
        <h3>Event Creation Confirmation</h3>
        <div class="pretext">
            <p>Thank you for creating an event with SWAreG.  Below is a summary of your event creation.  Please let us know if
            we can assist you further.</p>
        </div>
        <div class="round">
			<fieldset class="flow">
		      <legend>Basic Info</legend>
		      <table>
		          <tr><td>Name</td><td>${event?.name}</td></tr>
		          <tr><td>Year</td><td>${event?.year}</td></tr>
		          <tr><td>Numeral</td><td>${event?.numeral}</td></tr>
		          <tr><td>Home Page</td><td>${event?.homePage}</td></tr>
		     </table>
		     </fieldset>
		     <fieldset class="flow">
		     	<legend>Merchant Info</legend>
		      	<table>
		        	<tr><td>Pay Pal Email Address</td><td>${event?.merchantEmail}</td></tr>
		          	<tr><td>Currency</td><td>${event?.currency}</td></tr>
		          	<tr><td>Tax Rate</td><td>${event?.taxRate}</td></tr>
		     	</table>
		     </fieldset>
	    </div>
	    <div class="round">
	    	<fieldset class="flow">
	        <legend>Registration Levels</legend>
	        <table>
	        	<g:each in="${event?.levels}" var="level">
	            	<tr><td>${level?.name}</td><td>${String.format("%.2f",level?.price)} ${event?.currency}</td></tr>
	         	</g:each>
	     	</table>
	    	</fieldset>
	    </div>
    </body>
</html>