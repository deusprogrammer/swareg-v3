<html>
	<head>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'dashboard.css')}" type="text/css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	    <script src="${resource(dir: 'js', file: 'screenpos.js')}"></script>
	    <meta name="layout" content="flow" />
	    <script>
  			$(function() {
				$("#details-view").hide();
				$("#details-menu").hide();
  	  			
  	  			// Center modals
				centerElement("div#gift-modal");
				centerElement("div#view-modal");

  	  			// Initialize globals
  	  			selected_badge = 0;
  	  			badge_ownership = "";
  	  			last_event = null;
  	  			$previous_selection = null;

  	  			// Set menu selector
    			$("#owned-menu").menu();
    			$("#gifted-menu").menu();
    			$("#received-menu").menu();

    			var timeoutId = 0;

    			$('div.badge').mousedown(function(event) {
            		selected_badge      = $(this).attr("badge-number");
            		badge_ownership     = $(this).attr("badge-ownership");
            		$previous_selection = $(this);
            		last_event = event;

              		$("div#details-view").hide().load("${createLink(action:'viewBadge')}/" + selected_badge, function() {
                		$(this).fadeIn();
            		});
              		$("div#details-menu").hide();
            		
    			    timeoutId = setTimeout(function() {
                		switch(badge_ownership) {
                		case "gifted":
                    		console.log("GIFTED");
                			$("div#gifted-dropdown-menu").css({
                        		top:  last_event.clientY,
                    			left: last_event.clientX
                        	}).fadeIn();
                    		break;
                		case "owned":
                    		console.log("OWNED");
                			$("div#owned-dropdown-menu").css({
                        		top:  last_event.clientY,
                    			left: last_event.clientX
                        	}).fadeIn();
                    		break;
                		case "received":
                    		console.log("RECEIVED");
                			$("div#received-dropdown-menu").css({
                        		top:  last_event.clientY,
                    			left: last_event.clientX
                        	}).fadeIn();
                    		break;
                		}
        			}, 1000);
    			}).bind('mouseup mouseleave', function() {
    			    clearTimeout(timeoutId);
    			});
    			
    			$("div.mousable").mouseover(function() {
        			$("div.mousable").removeClass("selected");
	        		$(this).addClass("selected");
        		});

            	$("div.mousable").mousedown(function() {
            		$("div.mousable").removeClass("hard-selected");
            		$("div.dropdown-menu").hide();
                	$(this).addClass("hard-selected");
                });

            	$("div.event").click(function(event) {
                	event_number = $(this).attr("event-number");

                	$("div#details-view").hide().load("${createLink(action:'viewEvent')}/" + event_number, function() {
                    	$(this).fadeIn();
                    });
                	$("div#details-menu").hide().load("${createLink(action:'eventMenu')}/" + event_number, function() {
                    	$(this).fadeIn();
                	});
                });
        		
        		$(".dropdown-item").click(function() {
            		op = $(this).attr("op");
            		switch(op) {
            		case "gift":
                		console.log("GIFT CLICKED!");
                		$("span#gift-span").html($previous_selection.attr("badge-data"))
                		$("div#gift-modal").show();
                		break;
            		case "ungift":
            			console.log("UNGIFT CLICKED!");
    					jQuery.post(
   							"${createLink(action: 'ungift')}/" + selected_badge,
   							{},
   							function(data) {
   								if (data["success"]) {
   									alert("Gift deleted successfully!");
   								} else {
   									alert("Gift deletion failure!");
   								}
   								location.reload();
   							}
   						);
                		break;
            		case "accept":
            			console.log("ACCEPT CLICKED!");
    					jQuery.post(
    						"${createLink(action: 'accept')}/" + selected_badge,
    						{},
    						function(data) {
    							if (data["success"]) {
    								alert("Gift accepted successfully!");
    							} else {
    								alert("Gift acceptance failure!");
    							}
    							location.reload();
    						}
    					);
                		break;
            		}
            	});
            	
            	$("button#gift-send").click(function() {
                	email_address = $("input#gift-email").val();
					jQuery.post(
						"${createLink(action: 'gift')}/" + selected_badge,
						{to: email_address},
						function(data) {
							if (data["success"]) {
								alert("Gift created successfully!");
							} else {
								alert("Gift creation failure!");
							}
							location.reload();
						}
					);
                });
                
                $("button#gift-cancel").click(function() {
                	$("div#gift-modal").hide();
                	$("div.dropdown-menu").hide();
                	$("div.mousable").removeClass("hard-selected");
                	selected_badge = 0;
      	  			$previous_selection = null;
                });
  			});
 		</script>
	</head>
	<body>
		<div id="owned-dropdown-menu" class="dropdown-menu">
			<ul id="owned-menu">
				<li class="dropdown-item" op="gift"><a href="#">Gift</a></li>
			</ul>
		</div>
		<div id="received-dropdown-menu" class="dropdown-menu">
			<ul id="received-menu">
				<li class="dropdown-item" op="accept"><a href="#">Accept</a></li>
			</ul>
		</div>
		<div id="gifted-dropdown-menu" class="dropdown-menu">
			<ul id="gifted-menu">
				<li class="dropdown-item" op="ungift"><a href="#">Ungift</a></li>
			</ul>
		</div>
		<div id="gift-modal">
			<h3>Gift badge <span id="gift-span"></span> to User</h3>
			<label>Email Address</label><input type="text" id="gift-email"/><br />
			<button id="gift-send">Send</button><button id="gift-cancel">Cancel</button>
		</div>
		<div id="content">
			<sec:ifNotLoggedIn>
				<div id="login-view">
					<g:form controller="userFlow">
		                <table>
		                <tr><td>Email Address</td><td><g:textField name="emailAddress" /></td></tr>
		                <tr><td>Password</td><td><g:passwordField name="password" /></td></tr>
		                </table>
		                <div class="event-button">
		                <g:actionSubmit name="login" action="login" value="Login" />
		                <g:actionSubmit name="newUser" action="createUser" value="New User" />
		                <g:actionSubmit name="forgotPassword" action="resetPassword" value="Forgot Password" />
		                </div>
		            </g:form>
	            </div>
			</sec:ifNotLoggedIn>
			<sec:ifLoggedIn>
				<div id="possessions-view">
					<div id="badges-container">
						<h3>Your Badges</h3>
						<div id="badges-inner">
							<div id="badges">
								<g:each in="${received}" var="badge">
									<div class="mousable badge received" badge-ownership="received" badge-number="${badge.id}" badge-data="${badge}">
										<div class="badge-image">
											${badge.event.name}<br />
											${badge.event.year}
										</div>
										<div class="badge-text">
											${badge.registrationLevel.name[0..(badge.registrationLevel.name.size() < 4 ? badge.registrationLevel.name.size() - 1 : 4)]}
										</div>
									</div>
								</g:each>
								<g:each in="${badges}" var="badge">
									<div class="mousable badge owned" badge-ownership="owned" badge-number="${badge.id}" badge-data="${badge}">
										<div class="badge-image">
											${badge.event.name}<br />
											${badge.event.year}
										</div>
										<div class="badge-text">
											${badge.registrationLevel.name[0..(badge.registrationLevel.name.size() < 4 ? badge.registrationLevel.name.size() - 1 : 4)]}
										</div>
									</div>
								</g:each>
								<g:each in="${gifted}" var="badge">
									<div class="mousable badge gifted" badge-ownership="gifted" badge-number="${badge.id}" badge-data="${badge}">
										<div class="badge-image">
											${badge.event.name}<br />
											${badge.event.year}
										</div>
										<div class="badge-text">
											${badge.registrationLevel.name[0..(badge.registrationLevel.name.size() < 4 ? badge.registrationLevel.name.size() - 1 : 4)]}
										</div>
									</div>
								</g:each>
							</div>
						</div>
					</div>
					<div id="events-container">
						<h3>Your Events</h3>
						<div id="events-inner">
							<div id="events">
								<g:each in="${events}" var="event">
									<div class="event mousable" event-number="${event.id}">${event}</div>
								</g:each>
							</div>
							<a href="${createLink(controller: "eventFlow", action: "createEvent")}" title="New Event"><button style="height:110px;">+</button></a>
						</div>
					</div>
				</div>
				<div id="details-container">
					<div id="details-view">
					</div>
					<div id="details-menu">
					</div>
				</div>
			</sec:ifLoggedIn>
		</div>
	</body>
</html>