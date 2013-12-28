<html>
	<head>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'dashboard.css')}" type="text/css">
	</head>
	<body>
		<div id="content">
			<sec:ifNotLoggedIn>
			</sec:ifNotLoggedIn>
			<sec:ifLoggedIn>
				<div id="badges-container">
					<h3>Your Badges</h3>
					<div id="badges">
						<g:each in="${badges}" var="badge">
							<div class="badge">
								<div class="badge-image">
								</div>
								<div class="badge-text">
									${badge.event}<br />
									${badge.registrationLevel.name}<br />
								</div>
							</div>
						</g:each>
					</div>
				</div>
				<div id="events-container">
					<h3>Your Events</h3>
					<div id="events">
						<g:each in="${events}" var="event">
							<div class="event">${event}</div>
						</g:each>
					</div>
				</div>
			</sec:ifLoggedIn>
		</div>
	</body>
</html>