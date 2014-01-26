<html>
    <head>
    	<link rel="stylesheet" href="${resource(dir: 'css', file: 'dashboard.css')}" type="text/css">
        <meta name="layout" content="flow">
        <title>Event:Edit</title>
    </head>
    <body>
        <div id="content" style="width: 800px; margin: auto;">
        	<h3>Event Modify</h3>
        	<div class="dash-left">
	        <g:form action="update" id="${event.id}">
	            <fieldset class="flow">
		            <legend>Event Details</legend>
		            <table>
			            <tr><td>Name</td><td><g:textField name="name" value="${event?.name}" /></td></tr>
			            <tr><td>Year</td><td><g:textField name="year" value="${event?.year}"/></td></tr>
			            <tr><td>Numeral</td><td><g:textField name="numeral" value="${event?.numeral}"/></td></tr>
			            <tr><td>Homepage</td><td><g:textField name="homePage" value="${event?.homePage}"/></td></tr>
		            </table>
		            <g:submitButton name="update" value="Update" />
	            </fieldset>
	        </g:form>
	        </div>
	        <div class="dash-right">
		        <g:form action="update" id="${event.id}">
		        	<fieldset class="flow">
			            <legend>Skin</legend>
			            <table>
				            <tr><td>Logo URL</td><td><g:textField class="name" id="logo-field" name="logoUrl" value="${event?.logoUrl}" /></td></tr>
			            </table>
			            <g:submitButton name="update" value="Update" />
		            </fieldset>
		        </g:form>
	        	<g:form action="update" id="${event.id}">
	            	<fieldset class="flow">
			            <legend>Merchant Info</legend>
			            <table>
				            <tr><td>Merchant Email</td><td><g:textField name="merchantEmail" value="${event?.merchantEmail}" /></td></tr>
				            <tr><td>Currency</td><td><g:textField name="address2" value="${event?.currency}"/></td></tr>
				            <tr><td>Tax</td><td><g:textField name="taxRate" value="${event?.taxRate}"/></td></tr>
			            </table>
			            <g:submitButton name="update" value="Update" />
		            </fieldset>
		        </g:form>
	        </div>
        </div>
        <g:link controller="dashboard" action="index">Back to Dashboard</g:link>
    </body>
</html>