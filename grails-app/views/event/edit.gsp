<html>
    <head>
    	<link rel="stylesheet" href="${resource(dir: 'css', file: 'new.css')}" type="text/css">
        <meta name="layout" content="new">
        <title>Event:Edit</title>
    </head>
    <body>
    	<div id="content">
	        <div id="dash-content">
	        	<h3 class="center-text">Event Modify</h3>
	        	<div class="side-by-side centered">
            		<div class="dash short rounded side-by-side top">
		            	<span class="legend">Event Details</span>
		            	<div class="inner">
					        <g:form action="update" id="${event.id}">
					            <table>
						            <tr><td>Name</td><td><g:textField name="name" value="${event?.name}" /></td></tr>
						            <tr><td>Year</td><td><g:textField name="year" value="${event?.year}"/></td></tr>
						            <tr><td>Numeral</td><td><g:textField name="numeral" value="${event?.numeral}"/></td></tr>
						            <tr><td>Homepage</td><td><g:textField name="homePage" value="${event?.homePage}"/></td></tr>
					            </table>
					            <g:submitButton name="update" value="Update" />
					        </g:form>
				        </div>
			        </div>
 		            <div class="dash short rounded side-by-side top">
		            	<span class="legend">Skin</span>
		            	<div class="inner">
				        	<g:form action="update" id="${event.id}">
					            <table>
						            <tr><td>Logo URL</td><td><g:textField class="name" id="logo-field" name="logoUrl" value="${event?.logoUrl}" /></td></tr>
					            </table>
					            <g:submitButton name="update" value="Update" />
					        </g:form>
						</div>
			        </div>
	            	<div class="dash short rounded side-by-side top">
		            	<span class="legend">Merchant Info</span>
		            	<div class="inner">
			        		<g:form action="update" id="${event.id}">
					            <table>
						            <tr><td>Merchant Email</td><td><g:textField name="merchantEmail" value="${event?.merchantEmail}" /></td></tr>
						            <tr><td>Currency</td><td><g:textField name="address2" value="${event?.currency}"/></td></tr>
						            <tr><td>Tax</td><td><g:textField name="taxRate" value="${event?.taxRate}"/></td></tr>
					            </table>
					            <g:submitButton name="update" value="Update" />
					        </g:form>
						</div>
			        </div>
		        </div>
	        </div>
	        <g:link controller="dashboard" action="index">Back to Dashboard</g:link>
        </div>
    </body>
</html>