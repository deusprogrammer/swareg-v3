<html>
	<head>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'new.css')}" type="text/css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	    <script src="${resource(dir: 'js', file: 'screenpos.js')}"></script>
	</head>
	<body>
		<span class="legend">${badge.event}</span>
		<div class="inner scrollable">
			<table class="padded">
				<tbody>
					<tr><td class="label">Registration Level</td><td>${badge.registrationLevel.name}</td></tr>
					<tr><td class="label">Order #</td><td>${badge.order?.paymentId ?: "None"}</td></tr>
				</tbody>
			</table>
		</div>
	</body>
</html>