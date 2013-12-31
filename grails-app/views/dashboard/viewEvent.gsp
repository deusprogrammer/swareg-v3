<html>
	<head>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'dashboard.css')}" type="text/css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	    <script src="${resource(dir: 'js', file: 'screenpos.js')}"></script>
	</head>
	<body>
		<h3>${event}</h3>
		<table>
			<tbody>
				<tr><td class="label">Event</td><td>${event.name}</td></tr>
				<tr><td class="label">Year</td><td>${event.year}</td></tr>
				<tr><td class="label">Numeral</td><td>${event.numeral}</td></tr>
				<tr><td class="label">Uuid</td><td>${event.uuid}</td></tr>
				<tr><td class="label">Api-Key</td><td>${event.apiKey}</td></tr>
			</tbody>
		</table>
	</body>
</html>