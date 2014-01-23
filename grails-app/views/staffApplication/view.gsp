<html>
	<head>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'dashboard.css')}" type="text/css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	    <script src="${resource(dir: 'js', file: 'screenpos.js')}"></script>
	    <meta name="layout" content="flow" />
	</head>
	<body>
		<div id="content">
			<div style="height: 300px;">
				<h1 class="centered">Applications</h1>
				<div class="round dash-left">
					<div class="dash-inner">
						<h3>User</h3>
						<table class="padded">
							<tr><td>Email</td><td>${application.user.emailAddress}</td></tr>
							<tr><td>First Name</td><td>${application.user.firstName}</td></tr>
							<tr><td>Last Name</td><td>${application.user.lastName}</td></tr>
						</table>
					</div>
				</div>
				<div class="round dash-right">
					<div class="dash-inner scrollable">
						<h3>Application</h3>
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