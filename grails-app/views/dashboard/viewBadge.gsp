<html>
	<head>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'dashboard.css')}" type="text/css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	    <script src="${resource(dir: 'js', file: 'screenpos.js')}"></script>
	</head>
	<body>
		<h3>${badge}</h3>
		<table>
			<tbody>
				<tr><td class="label">Event</td><td>${badge.event}</td></tr>
				<tr><td class="label">Registration Level</td><td>${badge.registrationLevel.name}</td></tr>
				<tr><td class="label">Description</td><td>${badge.registrationLevel.description}</td></tr>
				<tr><td class="label">Order #</td><td>${badge.order.paymentId}</td></tr>
				<tr><td class="label">Id</td><td>${badge.order.id}</td></tr>
				<tr><td class="label">Operations</td><td></td></tr>
			</tbody>
		</table>
	</body>
</html>