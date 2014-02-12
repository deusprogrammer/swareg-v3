<html>
	<head>
	    <meta name="layout" content="new" />
	</head>
	<body>
		<div id="content">
			<h1 class="center-text">Application for ${position.title} for ${position.event}</h1>
			<div class="dash small-scaled shortest spaced">
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

			<div class="dash small-scaled rounded spaced">
				<span class="legend">Message</span>
				<div class="inner-x">
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