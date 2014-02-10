<html>
    <head>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'new.css')}" type="text/css">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script src="${resource(dir: 'js', file: 'screenpos.js')}"></script>
        <meta name="layout" content="new" />
        <script>
			$(function() {
			  $( ".datepicker" ).datepicker();
			});
		</script>
    </head>
    <body>
    	<div id="content">
	   		<div id="dash-content">
		    	<div class="dash small-scaled tall left rounded">
		    		<span class="legend">Registrations</span>
		    		<div class="inner scrollable"">
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
		        </div>
		        <div class="dash search right rounded">
		    		<span class="legend">Search</span>
		        	<div class="inner">
			        	<g:form action="searchRegistrations" id="${event.id}">
				        	<table>
				        		<tbody>
				        			<tr>
				        				<td class="label">Email Address</td>
				        				<td><g:textField name="emailAddress" /></td>
				        				<td><g:submitButton name="submit" value="Search"/></td>
				        			</tr>
				        			<tr>
				        				<td class="label">First Name</td>
				        				<td><g:textField name="firstName" /></td>
				        				<td></td>
				        			</tr>
				        			<tr>
				        				<td class="label">Last Name</td>
				        				<td><g:textField name="lastName" /></td>
				        				<td></td>
				        			</tr>
				        			<tr>
				        				<td class="label">Payment ID</td>
				        				<td><g:textField name="paymentId" /></td>
				        				<td></td>
				        			</tr>
				        			<tr>
				        			</tr>
				        		</tbody>
				        	</table>
			        	</g:form>
		        	</div>
		        </div>
	        </div>
        </div>
		<g:link action="index">Back to Dashboard</g:link>
    </body>
</html>