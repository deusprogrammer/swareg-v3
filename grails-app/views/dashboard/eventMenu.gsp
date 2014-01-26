<html>
	<head>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'dashboard.css')}" type="text/css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	    <script src="${resource(dir: 'js', file: 'screenpos.js')}"></script>
	</head>
	<body>
		<div>
			<fieldset class="menu-set" style="float: left;">
				<legend>Registration Menu</legend>
				<g:link class="menu-link" controller="registrationFlow" action="manualRegistration" id="${eventId}">Manual</g:link><br/>
				<g:link class="menu-link" action="viewRegistrations" id="${eventId}">View All</g:link><br/>
				<g:link class="menu-link" action="export" id="${eventId}">Export</g:link>
			</fieldset>
			<fieldset class="menu-set" style="float: right;">
				<legend>Tiers Menu</legend>
				<g:link class="menu-link" action="tierDash" id="${eventId}">Add/Remove Tiers</g:link><br/>
				<g:link class="menu-link" action="preRegOfferDash" id="${eventId}">Add/Remove Offers</g:link>
			</fieldset>
		</div>
		<div>
			<fieldset class="menu-set" style="float: left;">
				<legend>Staff Menu</legend>
				<g:link class="menu-link" action="manageStaff" id="${eventId}">Manage Staff</g:link><br/>
				Modify Mailing List
			</fieldset>
			<fieldset class="menu-set" style="float: right;">
				<legend>Event Menu</legend>
				<g:link class="menu-link" controller="event" action="edit" id="${eventId}">Manage Event</g:link><br/>
				Manage Files<br/>
				Manage Forum<br/>
				Manage Blog
			</fieldset>
		</div>
	</body>
</html>