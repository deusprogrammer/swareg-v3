<html>
	<head>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'new.css')}" type="text/css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	    <script src="${resource(dir: 'js', file: 'screenpos.js')}"></script>
	    <meta name="layout" content="new" />
	</head>
	<body>
		<div id="content">
			<h1 class="center-text">Create Position for ${event}</h1>
			<div class="dash small-scaled shortest spaced">
				<p>Please fill out the required details below.</p>
				<p>The permissions fields indicate what access they have to the event.  Some actions
				such as this one require admin permissions to act on.  Take care when assigning permissions.</p>
			</div>

			<div class="dash rounded wide-scaled side-by-side spaced">
				<span class="legend">Position</span>
				<div class="inner-x">
					<g:form action="save">
						<table>
							<g:hiddenField name="event.id" value="${event.id}" />
							<tr><td>Title</td><td><g:textField size="30" name="title" /></td></tr>
							<tr><td>Description</td><td><g:textArea cols="100" rows="10" name="description" /></td></tr>
							<tr><td>Read</td><td><g:checkBox name="read" /></td></tr>
							<tr><td>Write</td><td><g:checkBox name="write" /></td><td><g:submitButton name="submit" value="Create"/></td></tr>
							<tr><td>Admin</td><td><g:checkBox name="admin" /></td></tr>
						</table>
					</g:form>
				</div>
			</div>
		</div>
	</body>
</html>