<html>
	<head>
	    <meta name="layout" content="new" />
	</head>
	<body>
		<div id="content">
			<h1 class="center-text">Modify Position ${position?.title} for ${event}</h1>
			<div class="dash small-scaled shortest spaced">
				<p>Please fill out the required details below.</p>
				<p>The permissions fields indicate what access they have to the event.  Some actions
				such as this one require admin permissions to act on.  Take care when assigning permissions.</p>
			</div>
			<div class="dash rounded wide-scaled side-by-side spaced">
				<span class="legend">Modify Position</span>
				<div class="inner-x">
					<g:form action="update" id="${position?.id}">
						<table>
							<tr><td>Title</td><td><g:textField size="30" name="title" value="${position?.title}" /></td></tr>
							<tr><td>Description</td><td><g:textArea cols="100" rows="10" name="description" value="${position?.description}" /></td></tr>
							<tr><td>Read</td><td><g:checkBox name="read" value="${permissions?.read}" /></td></tr>
							<tr><td>Write</td><td><g:checkBox name="write" value="${permissions?.write}" /></td><td><g:submitButton name="submit" value="Create"/></td></tr>
							<tr><td>Admin</td><td><g:checkBox name="admin" value="${permissions?.admin}" /></td></tr>
						</table>
					</g:form>
				</div>
			</div>
		</div>
	</body>
</html>