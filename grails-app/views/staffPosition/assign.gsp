<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Manual-Registration</title>
    </head>
    <body>
        <h3>Assign a User to ${position.title} for ${position.event}</h3>
        <div class="pretext">
            <p>Enter an email address of a user you want to assign to this position.</p>
        </div>
        <div class="round" style="width:500px;">
            <g:form action="doAssign" id="${position.id}">
            	<fieldset class="flow">
            		<legend>Assign to Position</legend>
            		<table>
	            		<tbody>
		                	<tr>
		                		<td>
		                			<label>Email Address:</label>
		                		</td>
		                		<td>
		                			<g:textField id="emailAddress" name="emailAddress" />
		                		</td>
		                	</tr>
	                	</tbody>
                	</table>
                	<g:submitButton name="assign" value="Assign"/>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>