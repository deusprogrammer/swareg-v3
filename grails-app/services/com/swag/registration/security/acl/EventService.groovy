package com.swag.registration.security.acl

import grails.plugins.springsecurity.SpringSecurityService
import java.util.Map

import org.springframework.security.access.AccessDeniedException

import com.swag.registration.domain.Event
import com.swag.registration.domain.EventChildObject
import com.swag.registration.security.User

class EventService {
   SpringSecurityService springSecurityService
   
   void checkWrite(Event event) {
	   if (!event.userHasWrite(springSecurityService.currentUser)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }

   void checkRead(Event event) {
	   if (!event.userHasRead(springSecurityService.currentUser)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }

   void checkDelete(Event event) {
	   if (!event.userHasDelete(springSecurityService.currentUser)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }

   void checkAdmin(Event event) {
	   if (!event.userHasAdmin(springSecurityService.currentUser)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }

   void checkWrite(EventChildObject object) {
       Event event = Event.get(object.owner.id)
	   if (!event.userHasWrite(springSecurityService.currentUser)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }

   void checkRead(EventChildObject object) {
       Event event = Event.get(object.owner.id)
	   if (!event.userHasRead(springSecurityService.currentUser)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }

   void checkDelete(EventChildObject object) {
       Event event = Event.get(object.owner.id)
	   if (!event.userHasDelete(springSecurityService.currentUser)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }

   void checkAdmin(EventChildObject object) {
       Event event = Event.get(object.owner.id)
	   if (!event.userHasAdmin(springSecurityService.currentUser)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
}
