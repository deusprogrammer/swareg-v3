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
   			<h1 class="center-text">Registration Tiers</h1>
    		<div id="dash-content">
		    	<div class="dash small-scaled short left rounded">
		    		<span class="legend">New Tier</span>
		    		<div class="inner">
				        <g:form action="saveTier">
				        	<g:hiddenField name="eventId" value="${event.id}" />
					        <table>
					            <tbody>
					                <tr>
					                   <td class="label">Name</td><td><g:textField name="name" placeholder="Tier Name" /></td>
					                </tr>
		   			                <tr>
					                   <td class="label">Description</td><td><g:textField name="description" placeholder="Tier Description" /></td>
					                </tr>
					                <tr>
					                   <td class="label">Price</td><td><g:textField class="price" name="price" placeholder="0.00" /></td>
					                </tr>
					                <tr>
					                   <td class="label">Valid For</td><td><g:textField name="validFor" placeholder="1 year" /></td>
					                </tr>
		   			                <tr>
					                   <td class="label">Need Admin?</td><td><g:checkBox name="needAdmin" /></td>
					                </tr>
					            </tbody>
					        </table>
					        <g:submitButton name="submit" value="Submit" />
				        </g:form>
			        </div>
		        </div>
		        <div class="dash small-scaled short right rounded">
		        	<span class="legend">Existing Tiers</span>
		        	<div class="inner scrollable">
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
			        						<g:link class="menu-link" action="myTier" id="${level.id}">Modify</g:link>
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