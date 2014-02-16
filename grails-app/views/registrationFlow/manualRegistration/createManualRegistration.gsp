<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Manual-Registration</title>
    </head>
    <body>
        <h3>Choose Registration Level</h3>
        <div class="dash skinny-scaled shortest spaced">
        	<div class="inner">
            	<p>Enter an email address and choose a registration level.</p>
            </div>
        </div>
        <div class="dash rounded wide-scaled spaced">
        	<span class="legend">Manual Registration</span>
        	<div class="inner-x">
				<g:form>
            		<table class="pairs">
	            		<tbody>
		                	<tr>
		                		<td>
		                			<label>Email Address:</label>
		                		</td>
		                		<td>
		                			<g:textField id="emailAddress" name="emailAddress" />
		                		</td>
		                	</tr>
		                	<tr>
		                		<td>
		                			<label>Registration:</label>
		                		</td>
		                		<td>
		                			<g:select from="${event.levels.findAll{it.needAdmin}}" optionKey="id" name="tier" noSelection="${['null':'Select One...']}" />
		                		</td>
		                	</tr>
	                	</tbody>
                	</table>
                	<g:submitButton name="continue" value="Continue"/>
            	</g:form>
	        </div>
        </div>
    </body>
</html>