<html>
    <head>
        <meta name="layout" content="flow">
        <title>Event:Registration-Levels</title>
    </head>
    <body>
        <h1>Create Registration Level</h1>
        <div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>Now tell us what levels of registration you offer, you can enter as many as you like.</p>
          	</div>
        </div>
       	<div class="dash rounded skinny-scaled spaced">
        	<span class="legend">Registration Level Info</span>
        	<div class="inner-x">
		        <g:form>
		            <table class="pairs">
		                <tr><td>Name</td><td><g:textField name="name"/></td></tr>
		                <tr><td>Description</td><td><g:textField name="description"/></td></tr>
		                <tr><td>Base Price</td><td><g:textField name="price" pattern="[0-9]+\\.[0-9]{2}"/></td></tr>
		                <tr><td>Valid For</td><td><g:textField name="validFor"/></td></tr>
		            </table>
		            <g:submitButton name="addAnother" value="Add Another"/>
		            <g:submitButton name="done" value="Done"/>
		        </g:form>
	        </div>
        </div>
    </body>
</html>