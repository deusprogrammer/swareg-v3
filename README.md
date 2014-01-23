# SWAreG

## What is SWAreG?

SWAreG is a centralized, cloud based Registration System written in Groovy/Grails.  It is designed to allow a user to create an event and then manage their staff, attendees and badge orders.

SWAreG itself is the backend for a web service and a suite of onsite tools.  The onsite tools include:
+ An onsite registration tool.
+ A day of registration management tool.
+ A self-service kiosk.

Each of the above mentioned tools can function without an active internet connection, only requiring a network connection once a day to sync with the back end.
	
In the near future we will be adding Social Networking features and a customizable forum for each event.

## TODO
+ Finish position actions:
	+ modify
	+ assign
	+ unassign
+ Finish application actions:
	+ decline
+ Add emails for:
	+ being assigned a position
	+ creating an application for a position
	+ being accepted for a position
	+ being declined for a position
	
+ Allow event to set banner image.
+ Allow event to set color scheme.
+ Add file manager.
+ Integrate Grusaba.
+ Integrate Clogger.

+ Setup payment chaining.
+ Add views for events so you can add registrations to a cart and purchase them.
+ Close gift modal after clicking send.
+ Delete all old scaffolded views.

+ Clean up and consolidate CSS and views.
	+ Make more use of classes in CSS.
	+ Make more use of layouts and templates in views.
