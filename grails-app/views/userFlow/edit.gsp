<html>
    <head>
    	<link rel="stylesheet" href="${resource(dir: 'css', file: 'new.css')}" type="text/css">
        <meta name="layout" content="new">
        <title>User:Edit</title>
    </head>
    <body>
        <div id="content">
        	<h3>User Modify</h3>
            <div class="dash rounded skinny-scaled short side-by-side top">
				<span class="legend">User Data</span>
				<div class="inner">
			        <g:form action="update" id="${user.id}">
			            <table>
				            <tr><td>Email</td><td><g:textField class="email" name="emailAddress" size="30" value="${user?.emailAddress}" /></td></tr>
				            <tr><td>First Name</td><td><g:textField name="firstName" value="${user?.firstName}"/></td></tr>
				            <tr><td>Last Name</td><td><g:textField name="lastName" value="${user?.lastName}"/></td></tr>
			            </table>
			            <g:submitButton name="update" value="Update" />
			        </g:form>
		        </div>
            </div>
	        <div class="dash rounded skinny-scaled short side-by-side top">
	        	<span class="legend">Shipping</span>
	        	<div class="inner">
		       		<g:form action="update" id="${user.id}">
	                    <table>
				            <tr><td>Address 1</td><td><g:textField name="address1" value="${user?.streetAddress1}" /></td></tr>
				            <tr><td>Address 2</td><td><g:textField name="address2" value="${user?.streetAddress2}"/></td></tr>
				            <tr><td>City</td><td><g:textField name="city" value="${user?.city}"/></td></tr>
				            <tr><td>State</td><td><g:textField name="state" value="${user?.state}"/></td></tr>
				            <tr><td>Zip</td><td><g:textField name="zipCode" value="${user?.zipCode}"/></td></tr>
			            </table>
			            <g:submitButton name="update" value="Update" />
			        </g:form>
		        </div>
            </div>
        </div>
        <g:link controller="dashboard" action="index">Back to Dashboard</g:link>
    </body>
</html>