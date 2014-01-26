<html>
    <head>
        <meta name="layout" content="flow">
        <title>User:Edit</title>
    </head>
    <body>
        <div id="content" style="width: 800px; margin: auto;">
        	<h3>User Modify</h3>
	        <div class="round">
		        <g:form action="update" id="${user.id}" style="display: inline-block;">
		            <fieldset class="flow">
			            <legend>User Data</legend>
			            <table>
				            <tr><td>Email</td><td><g:textField class="email" name="emailAddress" value="${user?.emailAddress}" /></td></tr>
				            <tr><td>First Name</td><td><g:textField name="firstName" value="${user?.firstName}"/></td></tr>
				            <tr><td>Last Name</td><td><g:textField name="lastName" value="${user?.lastName}"/></td></tr>
			            </table>
			            <g:submitButton name="update" value="Update" />
		            </fieldset>
		        </g:form>
	            <g:form action="uploadAvatar" id="${user.id}" style="display: inline-block; float: right;">
	            	<fieldset class="flow">
		            	<legend>Avatar</legend>
			            <table>
				            <tr><td>Upload</td><td><input type="file" name="avatar-file" /></td></tr>
			            </table>
			            <g:submitButton name="upload" value="Upload"/>
		            </fieldset>
		        </g:form>
	        </div>
	        <div class="round">
	        	<fieldset class="flow">
	        		<legend>Address</legend>
		        	<g:form action="update" id="${user.id}">
		            	<fieldset class="flow">
				            <legend>Shipping</legend>
				            <table>
					            <tr><td>Address 1</td><td><g:textField name="address1" value="${user?.streetAddress1}" /></td></tr>
					            <tr><td>Address 2</td><td><g:textField name="address2" value="${user?.streetAddress2}"/></td></tr>
					            <tr><td>City</td><td><g:textField name="city" value="${user?.city}"/></td></tr>
					            <tr><td>State</td><td><g:textField name="state" value="${user?.state}"/></td></tr>
					            <tr><td>Zip</td><td><g:textField name="zipCode" value="${user?.zipCode}"/></td></tr>
				            </table>
			            </fieldset>
			            <fieldset class="flow">
			            	<legend>Billing</legend>
				            <table>
					            <tr><td>Address 1</td><td><g:textField name="address1" value="${user?.streetAddress1}" /></td></tr>
					            <tr><td>Address 2</td><td><g:textField name="address2" value="${user?.streetAddress2}"/></td></tr>
					            <tr><td>City</td><td><g:textField name="city" value="${user?.city}"/></td></tr>
					            <tr><td>State</td><td><g:textField name="state" value="${user?.state}"/></td></tr>
					            <tr><td>Zip</td><td><g:textField name="zipCode" value="${user?.zipCode}"/></td></tr>
				            </table>
			            </fieldset><br/>
			            <g:submitButton name="update" value="Update" />
			        </g:form>
		        </fieldset>
	        </div>
        </div>
    </body>
</html>