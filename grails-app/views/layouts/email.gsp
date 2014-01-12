<html>
	<head>
		<style>
			body {
				background-color: #333333;
			}
			
			.error {
				border: solid 2px #FF0000;
			}
			
			h3 {
				text-align: center;
			}
			
			div#main-container {
				margin: auto;
				width: 800px;
				background-color: #181818;
				color: #FFFFFF;
			}
			
			div#session {
				background-color: #333333;
			}
			
			div#banner {
			}
			
			div#footer {
			}
			
			div.round {
			}
			
			div.pretext {
			    width: 750px;
			    margin: 10px;
			    padding: 10px;
				background-color: #333333;
			}
			
			div.message {
				border: 2px solid;
				position: absolute;
				padding: 10px;
				margin: auto;
			}
			
			.centered {
				text-align: center;
				width: 600px;
				margin: auto;
			}
			
			.flow {
			    display: inline-block;
			    margin: 10px;
			    max-width: 500px;
			    padding: 10px;
			    border-radius: 25px;
			    background-color: #333333;
				border: 1px solid black;
			}
		</style>
	</head>
	<body>
		<div id="main-container">
	        <g:if test="${message}">
	            <div class="message" role="status">
	                <div class="message-inner">${message}</div>
	                <button id="message">Okay</button>
	            </div>
	        </g:if>
	        <div id="banner">
	        	<img src="${resource(dir: 'images', file: 'Swag-Logo.png')}" width="400px"/>
	        </div>
	        <div id="session">
	            <sec:ifLoggedIn>
	                Welcome back <sec:username/>!  <g:link controller='logout'>Logout?</g:link>
	            </sec:ifLoggedIn>
	        </div>
	        <g:layoutBody/>
			<div id="footer" role="contentinfo"></div>
			<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
			<g:javascript library="application"/>
			<r:layoutResources/>
		</div>
	</body>
</html>