<%@ page import="com.swag.registration.domain.Registration" %>
<%@ page import="com.swag.registration.domain.RegistrationLevel" %>
<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Manual-Registration</title>
    </head>
    <body>
        <h3>Choose Registration Level</h3>
        <div class="pretext">
            <p>Enter an email address and choose a registration level.</p>
        </div>
        <div class="round" style="width:500px;">
            <g:form>
            	<fieldset class="flow">
            		<legend>Manual Registration</legend>
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
    </body>
</html>