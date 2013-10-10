package com.swag.registration.security.acl

import java.util.Map

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.security.acls.model.Permission
import org.springframework.transaction.annotation.Transactional

import com.swag.registration.domain.Event
import com.swag.registration.domain.EventChildObject
import com.swag.registration.domain.PreRegistrationOffer
import com.swag.registration.domain.Registration
import com.swag.registration.domain.RegistrationLevel
import com.swag.registration.security.User

class EventService {

   static transactional = false

   def aclPermissionFactory
   def aclService
   def aclUtilService
   def springSecurityService
   def permissionService

   def grailsApplication

   void addPermission(Event event, String username, int permission) {
      addPermission event, username, aclPermissionFactory.buildFromMask(permission)
   }

   @PreAuthorize("hasPermission(#event, admin)")
   @Transactional
   void addPermission(Event event, String username, Permission permission) {
      aclUtilService.addPermission event, username, permission
   }

   @Transactional
   @PreAuthorize("hasPermission(#event, admin)")
   void deletePermission(Event event, String username, Permission permission) {
      def acl = aclUtilService.readAcl(event)

      // Remove all permissions associated with this particular
      // recipient (string equality to KISS)
      acl.entries.eachWithIndex { entry, i ->
         if (entry.sid.equals(recipient) && entry.permission.equals(permission)) {
            acl.deleteAce i
         }
      }

      aclService.updateAcl acl
   }

   @Transactional
   @PreAuthorize("hasRole('ROLE_USER')")
   Event create(Map params) {
      Event event = new Event(params)
      User user = springSecurityService.currentUser
      event.user = user
      event.save()

      // Grant the current principal administrative permission
      addPermission event, springSecurityService.authentication.name, BasePermission.ADMINISTRATION
      aclUtilService.changeOwner event, springSecurityService.authentication.name

      return event
   }

   @PreAuthorize("hasPermission(#id, 'com.swag.registration.domain.Event', write) or hasPermission(#id, 'com.swag.registration.domain.Event', admin) or hasRole('ROLE_GLOBAL')")
   Event get(long id) {
      return Event.get(id)
   }
   
   void checkWrite(EventChildObject object) {
	   Event event = Event.get(object.owner.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.WRITE) &&
		   !aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkRead(EventChildObject object) {
	   Event event = Event.get(object.owner.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.READ) &&
		   !aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkDelete(EventChildObject object) {
	   Event event = Event.get(object.owner.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.DELETE) &&
		   !aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkAdmin(EventChildObject object) {
	   Event event = Event.get(object.owner.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   /*
   void checkWrite(RegistrationLevel level) {
	   Event event = Event.get(level.event.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.WRITE) &&
		   !aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkWrite(PreRegistrationOffer offer) {
	   Event event = Event.get(offer.registrationLevel.event.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.WRITE) &&
		   !aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkWrite(Registration reg) {
	   Event event = Event.get(reg.event.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.WRITE) &&
		   !aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkRead(RegistrationLevel level) {
	   Event event = Event.get(level.event.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.READ) &&
		   !aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkRead(PreRegistrationOffer offer) {
	   Event event = Event.get(offer.registrationLevel.event.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.READ) &&
		   !aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkRead(Registration reg) {
	   Event event = Event.get(reg.event.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.READ) &&
		   !aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkDelete(RegistrationLevel level) {
	   Event event = Event.get(level.event.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication.principal, event, BasePermission.DELETE) &&
		   !aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkDelete(PreRegistrationOffer offer) {
	   Event event = Event.get(offer.registrationLevel.event.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication.principal, event, BasePermission.DELETE) &&
		   !aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkDelete(Registration reg) {
	   Event event = Event.get(reg.event.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.DELETE) &&
		   !aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkAdmin(RegistrationLevel level) {
	   Event event = Event.get(level.event.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkAdmin(PreRegistrationOffer offer) {
	   Event event = Event.get(offer.registrationLevel.event.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication.principal, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   
   void checkAdmin(Registration reg) {
	   Event event = Event.get(reg.event.id)
	   if (!aclUtilService.hasPermission(springSecurityService.authentication, event, BasePermission.ADMINISTRATION)) {
		   throw new AccessDeniedException("Access exception occured")
	   }
   }
   */

   @PreAuthorize("hasRole('ROLE_USER')")
   @PostFilter("hasPermission(filterObject, read) or hasPermission(filterObject, admin) or hasRole('ROLE_GLOBAL')")
   List<Event> list(Map params) {
      return Event.list(params)
   }

   int count() {
      return Event.count()
   }

   @Transactional
   @PreAuthorize("hasPermission(#event, write) or hasPermission(#event, admin)")
   void update(Event event, Map params) {
      event.properties = params
   }

   @Transactional
   @PreAuthorize("hasPermission(#event, delete) or hasPermission(#event, admin)")
   void delete(Event event) {
      event.delete()

      // Delete the ACL information as well
      aclUtilService.deleteAcl event
   }
}
