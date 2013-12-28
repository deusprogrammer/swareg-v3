<html>
	<head>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'dashboard.css')}" type="text/css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	    <script>
  			$(function() {
  	  			selected_badge = 0;
  	  			previous_selection = null;
    			$("#menu").menu();
    			$("div.badge").click(function(event) {
        			if (selected_badge == $(this).attr("badge-number")) {
        				$("div#dropdown-menu").hide();
        				previous_selection.removeClass("selected");
        				return
            		} else {
                		if (previous_selection) {
                			previous_selection.removeClass("selected");
                		}	            		
	        			selected_badge = $(this).attr("badge-number");
	        			previous_selection = $(this);
	        			$(this).addClass("selected");
	        			$("div#dropdown-menu").css({
		        			left: event.clientX, 
		        			top: event.clientY
		        		}).show();
            		}
        		});
        		$(".dropdown-item").click(function() {
            		op = $(this).attr("op");
            		switch(op) {
            		case "gift":
                		alert("GIFTING " + selected_badge);
                		break;
            		case "view":
                		alert("VIEWING " + selected_badge);
                		break;
            		}

            		$("div#dropdown-menu").hide();
            		previous_selection.removeClass("selected");
            	});
  			});
 		</script>
	</head>
	<body>
		<div id="dropdown-menu">
			<ul id="menu">
				<li class="dropdown-item" op="gift"><a href="#">Send Gift</a></li>
				<li class="dropdown-item" op="view"><a href="#">View</a></li>
			</ul>
		</div>
		<div id="content">
			<sec:ifNotLoggedIn>
			</sec:ifNotLoggedIn>
			<sec:ifLoggedIn>
				<div id="badges-container">
					<h3>Your Badges</h3>
					<div id="badges-inner">
						<div id="badges">
							<g:each in="${badges}" var="badge">
								<div class="badge" badge-number="${badge.id}">
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
			</sec:ifLoggedIn>
		</div>
	</body>
</html>