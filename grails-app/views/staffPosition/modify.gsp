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
			<h1 class="centered">Application for ${position.title} for ${event}</h1>
			<div class="pretext">
				<p>Simply tell us in the provided box below who you are, and why you feel you are a perfect fit for this position.</p>
				<p>If you are approved you will receive a confirmation email.  If we turn down your application you will also receive a similar email.</p>
			</div>

			<fieldset class="round">
				<legend>Message</legend>
				<g:form action="process" id="${position.id}">
					<g:textArea name="message" /><br/>
					<g:submitButton name="submit" value="Apply"/>
				</g:form>
			</fieldset>
		</div>
	</body>
</html>