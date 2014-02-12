<html>
	<head>
	    <meta name="layout" content="new" />
	</head>
	<body>
		<div id="content">
			<div class="spaced">
				<h1 class="center-text">Application</h1>
				<div class="dash rounded small-scaled shorter left">
					<span class="legend">User</span>
					<div class="inner-x">
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
			<div class="dash rounded small-scaled spaced centered" style="clear: both; margin:auto; top: 10px;">
				<div class="inner-x center-text">
					<g:link controller="staffApplication" action="approve" id="${application.id}"><button>Approve</button></g:link>
					<g:link controller="staffApplication" action="decline" id="${application.id}"><button>Decline</button></g:link>
				</div>
			</div>
		</div>
	</body>
</html>