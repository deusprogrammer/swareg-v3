<html>
    <head>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'dashboard.css')}" type="text/css">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script src="${resource(dir: 'js', file: 'screenpos.js')}"></script>
        <meta name="layout" content="flow" />
        <script>
			$(function() {
			  $( ".datepicker" ).datepicker();
			});
		</script>
    </head>
    <body>
    		<div id="dash-content" style="height: 600px;">
		    	<fieldset class="round" style="margin: auto; width: 600px; height: 400px; float: left;">
		    		<legend>Registrations</legend>
		    		<div class="inner scrollable" style="height: 375px;">
		    			<table class="padded">
		    				<tbody>
								<g:each in="${event.registrations.sort{it.user.emailAddress}}" var="badge">
									<tr>
										<td>${badge.user.emailAddress}</td>
										<td>${badge.user.firstName}</td>
										<td>${badge.user.lastName}</td>
										<td>${badge.registrationLevel}</td>
									</tr>
								</g:each>
							</tbody>
						</table>
					</div>
		        </fieldset>
		        <fieldset class="round" style="height: 175px; width: 300px; float:right; padding: 10px;">
		        	<legend>Search</legend>
		        	<div class="inner">
			        	<g:form action="searchRegistrations" id="${event.id}">
				        	<table>
				        		<tbody>
				        			<tr>
				        				<td class="label">Email Address</td>
				        				<td><g:textField name="emailAddress" /></td>
				        			</tr>
				        			<tr>
				        				<td class="label">First Name</td>
				        				<td><g:textField name="firstName" /></td>
				        			</tr>
				        			<tr>
				        				<td class="label">Last Name</td>
				        				<td><g:textField name="lastName" /></td>
				        			</tr>
				        			<tr>
				        				<td class="label">Payment ID</td>
				        				<td><g:textField name="paymentId" /></td>
				        			</tr>
				        		</tbody>
				        	</table>
			        		<g:submitButton name="submit" value="Search"/>
			        	</g:form>
		        	</div>
		        </fieldset>
	        </div>
		</div>
		<g:link action="index">Back to Dashboard</g:link>
    </body>
</html>