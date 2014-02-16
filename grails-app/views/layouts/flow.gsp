<%@ page import="com.swag.registration.security.User" %>

<!DOCTYPE html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="SWAreG- Registration System"/></title>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'new.css')}" type="text/css">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
		<script src="${resource(dir: 'js', file: 'screenpos.js')}"></script>
		<script src="${resource(dir: 'js', file: 'new.js')}"></script>
		<g:layoutHead/>
        <r:layoutResources/>
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
	        	<img src="${resource(dir: 'images', file: 'Swag-Logo.png')}" height="75px"/>
	        </div>
	        <div id="body-content">
	        	<g:layoutBody/>
	        </div>
			<div id="footer" role="contentinfo"></div>
			<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
			<g:javascript library="application"/>
			<r:layoutResources/>
		</div>
	</body>
</html>
