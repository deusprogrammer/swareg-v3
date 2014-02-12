<html>
    <head>
        <meta name="layout" content="new">
        <title>Registration:Manual-Registration</title>
    </head>
    <body>
        <h3 class="center-text">Assign a User to ${position.title} for ${position.event}</h3>
        <div class="dash small-scaled shortest spaced">
        	<div class="inner">
            	<p>Enter an email address of a user you want to assign to this position.</p>
            </div>
        </div>
        <div class="dash skinny-scaled rounded spaced">
        	<span class="legend">Assign to Position</span>
        	<div class="inner-x">
	            <g:form action="doAssign" id="${position.id}">
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
	            </g:form>
            </div>
        </div>
    </body>
</html>