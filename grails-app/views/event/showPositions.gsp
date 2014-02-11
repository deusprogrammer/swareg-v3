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
			<h1 class="center-text">Positions for ${event}</h1>
			<div class="dash skinny-scaled short rounded">
				<span class="legend">Positions</span>
				<div class="inner scrollable">
					<table>
						<g:each in="${positions.sort { it.title}}" var="position">
							<g:if test="${position.available}">
								<tr><td>${position.title}</td><td></td><td><g:link controller="staffPosition" action="apply" id="${position.id}">Apply</g:link></td></tr>
							</g:if>
							<g:else>
								<tr><td>${position.title}</td><td>${position.user.firstName}</td><td></td></tr>
							</g:else>
						</g:each>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>