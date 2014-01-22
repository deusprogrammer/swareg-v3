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
    	<div id="content">
   			<center><h1>Registration Tiers</h1></center>
    		<div id="dash-content">
		    	<div class="round" style="height: 250px; float: left;">
			        <h3>Add Registration Tier for ${event}</h3>
			        <g:form action="saveTier">
			        	<g:hiddenField name="eventId" value="${event.id}" />
				        <table>
				            <tbody>
				                <tr>
				                   <td class="label">Name</td><td><g:textField name="name" /></td>
				                </tr>
	   			                <tr>
				                   <td class="label">Description</td><td><g:textField name="description" /></td>
				                </tr>
				                <tr>
				                   <td class="label">Price</td><td><g:textField name="price" /></td>
				                </tr>
				                <tr>
				                   <td class="label">Valid For</td><td><g:textField name="validFor" /></td>
				                </tr>
	   			                <tr>
				                   <td class="label">Need Admin?</td><td><g:checkBox name="needAdmin" /></td>
				                </tr>
				            </tbody>
				        </table>
				        <g:submitButton name="submit" value="Submit" />
			        </g:form>
		        </div>
		        <div class="round" style="height: 250px; width: 300px; float:right;">
		        	<h3>Existing Tiers</h3>
		        	<div style="height: 150px; overflow-y: scroll;">
			        	<table class="padded">
			        		<tbody>
			        			<g:each in="${event.levels}" var="level">
			        				<tr>
			        					<g:if test="${level.needAdmin}">
				        					<td class="highlighted">${level.name}</td>
			        					</g:if>
			        					<g:else>
			        						<td>${level.name}</td>
			        					</g:else>
			        					<td>
			        						<g:link action="deleteTier" id="${level.id}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">Delete</g:link>
			        					</td>
			        				</tr>
			        			</g:each>
			        		</tbody>
			        	</table>
		        	</div>
		        </div>
	        </div>
		</div>
		<g:link action="index">Back to Dashboard</g:link>
    </body>
</html>