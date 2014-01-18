<html>
    <head>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'dashboard.css')}" type="text/css">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script src="${resource(dir: 'js', file: 'screenpos.js')}"></script>
        <script>
			$(function() {
			  $( ".datepicker" ).datepicker();
			});
		</script>
    </head>
    <body>
        <h3>Add Pre Registration Offer for ${event}</h3>
        <g:form action="savePreRegOffer">
	        <table>
	            <tbody>
	                <tr>
	                   <td class="label">Badge Tier</td>
	                   <td>
	                       <g:select from="${event.levels}" optionKey="id" name="tier" noSelection="${['null':'Select One...']}" />
	                   </td>
	                </tr>
	                <tr>
	                   <td class="label">Price</td><td><g:textField name="price" /></td>
	                </tr>
	                <tr>
	                   <td class="label">Start Date</td><td><input type="text" class="datepicker" name="startDate" /></td>
                    </tr>
	                <tr>
	                   <td class="label">End Date</td><td><input type="text" class="datepicker" name="endDate" /></td>
	                </tr>
	            </tbody>
	        </table>
	        <g:submitButton name="submit" value="Submit" />
        </g:form>
    </body>
</html>