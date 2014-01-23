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
    		<h1 class="centered">Manage Staff for ${event}</h1>
    		<div id="dash-content">
		    	<fieldset class="dash-left round">
		    		<legend>Positions</legend>
			    	<div class="dash-inner scrollable">
						<table class="padded bbottom-cell">
							<g:each in="${event.positions.sort { it.title}}" var="position">
								<g:if test="${position.available}">
									<tr>
										<td>
											${position.title}
										</td>
										<td>
										</td>
										<td>
											<g:link controller="staffPosition" action="assign" id="${position.id}" class="menu-link">Assign</g:link><br/>
											<g:link controller="staffPosition" action="delete" id="${position.id}" class="menu-link">Delete</g:link><br/>
											<g:link controller="staffPosition" action="modify" id="${position.id}" class="menu-link">Modify</g:link><br/>
										</td>
									</tr>
								</g:if>
								<g:else>
									<tr>
										<td>
											${position.title}
										</td>
										<td>
											${position.user.lastName}, ${position.user.firstName}
										</td>
										<td>
											<g:link controller="staffPosition" action="unassign" id="${position.id}" class="menu-link">Unassign</g:link><br/>
											<g:link controller="staffPosition" action="delete" id="${position.id}" class="menu-link">Delete</g:link><br/>
											<g:link controller="staffPosition" action="modify" id="${position.id}" class="menu-link">Modify</g:link>
										</td>
									</tr>
								</g:else>
							</g:each>
						</table>
		        	</div>
		        	<g:link controller="staffPosition" action="create" id="${event.id}"><button style="height:200px; float: right">+</button></g:link>
		        </fieldset>
		        <fieldset class="dash-right round">
		        	<legend>Applications</legend>
		        	<div class="dash-inner scrollable">
						<table class="padded bbottom-cell">
							<g:each in="${event.positions.sort {it.title}}" var="position">
								<g:if test="${position.available}">
									<g:each in="${position.applicants.sort {it.user.lastName}}" var="applicant">
										<tr>
											<td>
												${position.title}
											</td>
											<td>
												${applicant.user.lastName}, ${applicant.user.firstName}
											</td>
											<td>
												<g:link controller="staffApplication" action="approve" id="${applicant.id}" class="menu-link">Approve</g:link><br/>
												<g:link controller="staffApplication" action="decline" id="${applicant.id}" class="menu-link">Decline</g:link><br/>
												<g:link controller="staffApplication" action="view" id="${applicant.id}" class="menu-link">View</g:link>
											</td>
										</tr>
									</g:each>
								</g:if>
							</g:each>
						</table>		        		
		        	</div>
		        </fieldset>
	        </div>
		</div>
		<g:link action="index">Back to Dashboard</g:link>
	</body>
</html>