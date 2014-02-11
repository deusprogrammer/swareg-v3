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
			<div style="height: 300px;">
				<h1 class="center-text">Application</h1>
				<div class="dash rounded small-scaled shorter left">
					<span class="legend">User</span>
					<div class="inner">
						<table class="padded">
							<tr><td>Email</td><td>${application.user.emailAddress}</td></tr>
							<tr><td>First Name</td><td>${application.user.firstName}</td></tr>
							<tr><td>Last Name</td><td>${application.user.lastName}</td></tr>
						</table>
					</div>
				</div>
				<div class="dash rounded small-scaled shorter right">
					<div class="inner scrollable">
						<span class="legend">Application</span>
						<pre>${application.message}</pre>
					</div>
				</div>
			</div>
			<div style="height: 300px;">
				<div class="round centered">
					<g:link controller="staffApplication" action="approve" id="${application.id}"><button>Approve</button></g:link>
					<g:link controller="staffApplication" action="decline" id="${application.id}"><button>Decline</button></g:link>
				</div>
			</div>
		</div>
	</body>
</html>