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
			<h1 class="center-text">Application for ${position.title} for ${position.event}</h1>
			<div class="dash small-scaled shorter spaced">
				<div class="inner">
					<p>Simply tell us in the provided box below who you are, and why you feel you are a perfect fit for this position.</p>
					<p>If you are approved you will receive a confirmation email.  If we turn down your application you will also receive a similar email.</p>
				</div>
			</div>
			
			<div class="dash small-scaled shorter rounded spaced">
				<span class="legend">Description</span>
				<div class="inner scrollable">
					<b>${position.title}</b>: ${position.description}
				</div>
			</div>

			<div class="dash small-scaled short rounded spaced">
				<span class="legend">Message</span>
				<div class="inner">
					<g:form action="process" id="${position.id}">
						<g:textArea rows="10" cols="400" class="inner scrollable" name="message" /><br/>
						<br/>
						<g:submitButton name="submit" value="Apply"/>
					</g:form>
				</div>
			</div>
		</div>
	</body>
</html>