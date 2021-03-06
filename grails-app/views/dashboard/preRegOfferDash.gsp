<html>
    <head>
        <meta name="layout" content="new" />
        <script>
			$(function() {
			  $( ".datepicker" ).datepicker();
			});
		</script>
    </head>
    <body>
    	<div id="content">
    		<h1 class="center-text">Pre Registration Offers</h1>
    		<div id="dash-content">
		    	<div class="dash small-scaled short left rounded">
			        <span class="legend">New Offer</span>
			        <div class="inner">
				        <g:form class="validate-me" model="preRegistrationOffer" action="savePreRegOffer">
					        <table>
					            <tbody>
					                <tr>
					                   <td class="label">Badge Tier</td>
					                   <td>
					                       <g:select from="${event.levels.findAll {!it.needAdmin}}" optionKey="id" name="tier" noSelection="${['null':'Select One...']}" />
					                   </td>
					                </tr>
					                <tr>
					                   <td class="label">Price</td><td><g:textField name="price" placeholder="0.00" /></td>
					                </tr>
					                <tr>
					                   <td class="label">Start Date</td><td><input type="text" class="datepicker ignore" name="startDate" /></td>
				                    </tr>
					                <tr>
					                   <td class="label">End Date</td><td><input type="text" class="datepicker ignore" name="endDate" /></td>
					                </tr>
					            </tbody>
					        </table>
					        <g:submitButton name="submit" value="Submit" />
				        </g:form>
			        </div>
		        </div>
		        <div class="dash small-scaled short right rounded">
			        <span class="legend">Existing Offers</span>
			        <div class="inner scrollable">
			        	<table>
			        		<tbody>
			        			<g:each in="${event.levels.findAll {!it.needAdmin}}" var="level">
			        				<tr><td>${level.name}</td><td></td><td></td></tr>
			        				<g:each in="${level.preRegOffers.sort{it.startDate}}" var="offer">
			        					<tr><td></td><td>${offer}</td><td><g:link action="deletePreRegOffer" id="${offer.id}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">Delete</g:link></td></tr>
			        				</g:each>
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