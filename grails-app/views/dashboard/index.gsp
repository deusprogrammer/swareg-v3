<html>
	<head>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'dashboard.css')}" type="text/css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	    <script src="${resource(dir: 'js', file: 'screenpos.js')}"></script>
	    <script>
  			$(function() {
  	  			// Center modals
				centerElement("div#gift-modal");
				centerElement("div#view-modal");

  	  			// Initialize globals
  	  			selected_badge = 0;
  	  			$previous_selection = null;

  	  			// Set menu selector
    			$("#owned-menu").menu();
    			$("#gifted-menu").menu();
    			$("#received-menu").menu();
    			
    			$("div.badge").mouseover(function() {
	        		$(this).addClass("selected");
        		});

        		$("div.badge").mouseout(function() {
        			$(this).removeClass("selected");
            	});

    			// Set click listeners
    			$("div.badge.owned").click(function(event) {
        			console.log("UNGIFTED");
        			$("div.dropdown-menu").hide();
        			if (selected_badge == $(this).attr("badge-number")) {
        				selected_badge = 0;
        				$("div.dropdown-menu").hide();
        				$previous_selection.removeClass("hard-selected");
        				return
            		} else {
                		if ($previous_selection) {
                			$previous_selection.removeClass("hard-selected");
                		}	            		
	        			selected_badge = $(this).attr("badge-number");
	        			$previous_selection = $(this);
	        			$(this).addClass("hard-selected");
	        			$("div#owned-dropdown-menu").css({
		        			left: event.clientX, 
		        			top: event.clientY
		        		}).show();
            		}
        		});
    			// Set click listeners
    			$("div.badge.gifted").click(function(event) {
        			console.log("GIFTED");
        			$("div.dropdown-menu").hide();
        			if (selected_badge == $(this).attr("badge-number")) {
        				selected_badge = 0;
        				$("div.dropdown-menu").hide();
        				$previous_selection.removeClass("hard-selected");
        				return
            		} else {
                		if ($previous_selection) {
                			$previous_selection.removeClass("hard-selected");
                		}
	        			selected_badge = $(this).attr("badge-number");
	        			$previous_selection = $(this);
	        			$(this).addClass("hard-selected");
	        			$("div#gifted-dropdown-menu").css({
		        			left: event.clientX, 
		        			top: event.clientY
		        		}).show();
            		}
        		});
    			// Set click listeners
    			$("div.badge.received").click(function(event) {
        			console.log("RECEIVED");
        			$("div.dropdown-menu").hide();
        			if (selected_badge == $(this).attr("badge-number")) {
            			selected_badge = 0;
            			$("div.dropdown-menu").hide();
        				$previous_selection.removeClass("hard-selected");
        				return
            		} else {
                		if ($previous_selection) {
                			$previous_selection.removeClass("hard-selected");
                		}
	        			selected_badge = $(this).attr("badge-number");
	        			$previous_selection = $(this);
	        			$(this).addClass("hard-selected");
	        			$("ul#menu").html("")
	        			$("div#received-dropdown-menu").css({
		        			left: event.clientX, 
		        			top: event.clientY
		        		}).show();
            		}
        		});
        		$(".dropdown-item").click(function() {
            		op = $(this).attr("op");
            		switch(op) {
            		case "gift":
                		// Write badge data to modal
                		$("span#gift-span").html($previous_selection.attr("badge-data"))
                		$("div#gift-modal").show();
                		break;
            		case "ungift":
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
            		case "view":
                		// Write badge data to modal
                		$("div#view-modal").show();
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
                	$previous_selection.removeClass("hard-selected");
                	selected_badge = 0;
      	  			$previous_selection = null;
                });
                $("button#view-okay").click(function() {
                	$("div#view-modal").hide();
                	$previous_selection.removeClass("hard-selected");
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
				<li class="dropdown-item" op="view"><a href="#">View</a></li>
			</ul>
		</div>
		<div id="received-dropdown-menu" class="dropdown-menu">
			<ul id="received-menu">
				<li class="dropdown-item" op="accept"><a href="#">Accept</a></li>
				<li class="dropdown-item" op="view"><a href="#">View</a></li>
			</ul>
		</div>
		<div id="gifted-dropdown-menu" class="dropdown-menu">
			<ul id="gifted-menu">
				<li class="dropdown-item" op="ungift"><a href="#">Ungift</a></li>
				<li class="dropdown-item" op="view"><a href="#">View</a></li>
			</ul>
		</div>
		<div id="gift-modal">
			<h3>Gift badge <span id="gift-span"></span> to User</h3>
			<label>Email Address</label><input type="text" id="gift-email"/><br />
			<button id="gift-send">Send</button><button id="gift-cancel">Cancel</button>
		</div>
		<div id="view-modal">
			<button id="view-okay">Okay</button>
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
									<div class="badge received" badge-number="${badge.id}" badge-data="${badge}">
										<div class="badge-image">
											${badge.event.name}<br />
											${badge.event.year}
										</div>
										<div class="badge-text">
											${badge.registrationLevel.name}
										</div>
									</div>
								</g:each>
								<g:each in="${badges}" var="badge">
									<div class="badge owned" badge-number="${badge.id}" badge-data="${badge}">
										<div class="badge-image">
											${badge.event.name}<br />
											${badge.event.year}
										</div>
										<div class="badge-text">
											${badge.registrationLevel.name}
										</div>
									</div>
								</g:each>
								<g:each in="${gifted}" var="badge">
									<div class="badge gifted" badge-number="${badge.id}" badge-data="${badge}">
										<div class="badge-image">
											${badge.event.name}<br />
											${badge.event.year}
										</div>
										<div class="badge-text">
											${badge.registrationLevel.name}
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
									<div class="event">${event}</div>
								</g:each>
							</div>
							<a href="${createLink(controller: "eventFlow", action: "createEvent")}" title="New Event"><button style="height:210px;">+</button></a>
						</div>
					</div>
				</div>
				<div id="details-view">
				</div>
			</sec:ifLoggedIn>
		</div>
	</body>
</html>