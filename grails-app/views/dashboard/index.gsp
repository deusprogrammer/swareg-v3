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
    			$("#menu").menu();

    			$("div.badge").mouseover(function() {
	        		$(this).addClass("selected");
        		});

        		$("div.badge").mouseout(function() {
        			$(this).removeClass("selected");
            	});

    			// Set click listeners
    			$("div.badge").click(function(event) {
        			if (selected_badge == $(this).attr("badge-number")) {
        				$("div#dropdown-menu").hide();
        				$previous_selection.removeClass("hard-selected");
        				return
            		} else {
                		if ($previous_selection) {
                			$previous_selection.removeClass("hard-selected");
                		}	            		
	        			selected_badge = $(this).attr("badge-number");
	        			$previous_selection = $(this);
	        			$(this).addClass("hard-selected");
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
                		// Write badge data to modal
                		$("span#gift-span").html($previous_selection.attr("badge-data"))
                		$("div#gift-modal").show();
                		break;
            		case "view":
                		// Write badge data to modal
                		$("div#view-modal").show();
                		break;
            		}

            		$("div#dropdown-menu").hide();
            		$previous_selection.removeClass("selected");
            	});
            	$("button#gift-send").click(function() {
                	// Do ajax call to send gift
            		$("div#gift-modal").hide();
            		$previous_selection.removeClass("hard-selected");
            		selected_badge = 0;
      	  			$previous_selection = null;
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
		<div id="dropdown-menu">
			<ul id="menu">
				<li class="dropdown-item" op="gift"><a href="#">Send Gift</a></li>
				<li class="dropdown-item" op="view"><a href="#">View</a></li>
			</ul>
		</div>
		<div id="gift-modal">
			<h3>Gift badge <span id="gift-span"></span> to User</h3>
			<label>Email Address</label><input type="text" /><br />
			<button id="gift-send">Send</button><button id="gift-cancel">Cancel</button>
		</div>
		<div id="view-modal">
			<button id="view-okay">Okay</button>
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
								<div class="badge" badge-number="${badge.id}" badge-data="${badge}">
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