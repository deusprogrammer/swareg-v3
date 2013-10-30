<%@ page import="com.swag.registration.domain.Event" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}
            
			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<h1>SWAreG Registration System</h1>
           <div id="dashboard">
            <sec:ifNotLoggedIn>
	            <g:form controller="userFlow">
	                <table>
	                <tr><td>Email Address</td><td><g:textField name="emailAddress" /></td></tr>
	                <tr><td>Password</td><td><g:passwordField name="password" /></td></tr>
	                </table>
	                <div class="event-button">
	                <g:actionSubmit name="login" action="login" value="Login" />
	                <g:actionSubmit name="newUser" action="createUser" value="New User" />
	                </div>
	            </g:form>
            </sec:ifNotLoggedIn>
            <sec:ifLoggedIn>
                   <h3>Dashboard</h3>
                   <div class="event-list">
                       <h6>Events</h6>
                    <g:displayEvents />
                   </div>
                   <div class="event-button">
                       <g:form controller="eventFlow">
                           <g:actionSubmit action="createEvent" value="New Event" />
                       </g:form>
                   </div>
            </sec:ifLoggedIn>
           </div>
	</body>
</html>
