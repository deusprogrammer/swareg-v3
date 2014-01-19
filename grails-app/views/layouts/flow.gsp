<%@ page import="com.swag.registration.security.User" %>

<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'flow.css')}" type="text/css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
		<script src="${resource(dir: 'js', file: 'screenpos.js')}"></script>
        <script>
            function validate(selector, filter, message) {
                if (!filter.test($(selector).val())) {
                    $(selector).addClass("error");
                    $(selector).tooltip({ show: { effect: "bounce", duration: 800 }});
                    $(selector).tooltip({ position: {at: "right+105"}});
                    $(selector).attr("title", message);
                    $(selector).tooltip("open");
                } else {
                    $(selector).removeClass("error");
                    $(selector).tooltip({content : ""});
                    $(selector).tooltip("close");
                }
            }
        
            $(function() {
                $("#date").datepicker();
                $("#startDate").datepicker();
                $("#endDate").datepicker();

                $(".password-confirm").blur(function() {
                    if ($(this).val() != $(".password").val()) {
                        $(this).addClass("error");
                        $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
                        $(this).tooltip({ position: {at: "right+105"}});
                        $(this).attr("title", "Passwords must match");
                        $(this).tooltip("open");
                    } else {
                        $(this).removeClass("error");
                        $(this).tooltip({content : ""});
                        $(this).tooltip("close");
                    }
                });

                $(".email").blur(function() {
                	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                    if (!filter.test($(this).val())) {
                        $(this).addClass("error");
                        $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
                        $(this).tooltip({ position: {at: "right+105"}});
                        $(this).attr("title", "Invalid email address");
                        $(this).tooltip("open");
                    } else {
                        $(this).removeClass("error");
                        $(this).tooltip({content : ""});
                        $(this).tooltip("close");
                    }
                });

                $(".url").blur(function() {
                    var filter = /[-a-zA-Z0-9@:%_\+.~#?&//=]{2,256}\.[a-z]{2,4}\b(\/[-a-zA-Z0-9@:%_\+.~#?&//=]*)?/;
                    if (!filter.test($(this).val())) {
                        $(this).addClass("error");
                        $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
                        $(this).tooltip({ position: {at: "right+105"}});
                        $(this).attr("title", "Malformed url");
                        $(this).tooltip("open");
                    } else {
                        $(this).removeClass("error");
                        $(this).tooltip({content : ""});
                        $(this).tooltip("close");
                    }
                });

                $(".price").blur(function() {
                    var filter = /[0-9]+.[0-9]+/;
                    if (!filter.test($(this).val())) {
                        $(this).addClass("error");
                        $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
                        $(this).tooltip({ position: {at: "right+105"}});
                        $(this).attr("title", "Malformed price");
                        $(this).tooltip("open");
                    } else {
                        $(this).removeClass("error");
                        $(this).tooltip({content : ""});
                        $(this).tooltip("close");
                    }
                });

                $(".validFor").blur(function() {
                    var filter = /[0-9]+ (year|month|day|hour|minute)+s*/;
                    if (!filter.test($(this).val())) {
                        $(this).addClass("error");
                        $(this).tooltip({ show: { effect: "bounce", duration: 800 }});
                        $(this).tooltip({ position: {at: "right+105"}});
                        $(this).attr("title", "Invalid time period");
                        $(this).tooltip("open");
                    } else {
                        $(this).removeClass("error");
                        $(this).tooltip({content : ""});
                        $(this).tooltip("close");
                    }
                });

                $("#sameAsShipping").click(function() {
                    if ($(this).is(':checked')) {
                    	$(".disablable").each(function() {
                        	$(this).prop('disabled', true);
                    	});
                    } else {
                        $(".disablable").each(function() {
                            $(this).prop('disabled', false);
                        });
                    }
                });

                $(function() {
                    $(document).tooltip();
                });
            });
        </script>
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
	        	<img src="${resource(dir: 'images', file: 'Swag-Logo.png')}" height="100px"/>
	        </div>
	        <div id="session">
	            <sec:ifLoggedIn>
	                Welcome back <sec:username/>!  <g:link controller='logout'>Logout?</g:link>
	            </sec:ifLoggedIn>
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
