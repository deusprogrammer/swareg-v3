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
				Manual<br/>
				View All<br/>
				Export<br/>
			</fieldset>
			<fieldset class="menu-set" style="float: right;">
				<legend>Tiers Menu</legend>
				Add Tier<br/>
				<g:link class="menu-link" action="addPreRegOffer" id="${eventId}">Add Offer</g:link><br/>
			</fieldset>
		</div>
		<div>
			<fieldset class="menu-set">
				<legend>Staff Menu</legend>
				Promote/Demote<br/>
				Add/Remove<br/>
			</fieldset>
		</div>
	</body>
</html>