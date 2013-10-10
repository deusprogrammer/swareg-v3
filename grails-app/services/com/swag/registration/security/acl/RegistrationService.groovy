package com.swag.registration.security.acl

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.security.acls.model.Permission
import org.springframework.transaction.annotation.Transactional

import com.swag.registration.domain.Registration

class RegistrationService {

   static transactional = false

   def aclPermissionFactory
   def aclService
   def aclUtilService
   def springSecurityService
   def permissionService

   void addPermission(Registration registration, String username, int permission) {
      addPermission registration, username,
           aclPermissionFactory.buildFromMask(permission)
   }

   @PreAuthorize("hasPermission(#registration, admin)")
   @Transactional
   void addPermission(Registration registration, String username,
                      Permission permission) {
      aclUtilService.addPermission registration, username, permission
   }

   @Transactional
   @PreAuthorize("hasPermission(#registration, admin)")
   void deletePermission(Registration registration, String username, Permission permission) {
      def acl = aclUtilService.readAcl(registration)

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
   Registration create(Map params) {
      Registration registration = new Registration(params)
      registration.save()

      // Grant the current principal administrative permission
      //addPermission registration, springSecurityService.authentication.name, asePermission.ADMINISTRATION
      permissionService.addPermission registration, springSecurityService.authentication.name, asePermission.ADMINISTRATION

      registration
   }

   @PreAuthorize("hasPermission(#id, 'com.swag.registration.domain.Registration', read) or hasPermission(#id, 'com.swag.registration.domain.Registration', admin)")
   Registration get(long id) {
      Registration.get id
   }

   @PreAuthorize("hasRole('ROLE_USER')")
   @PostFilter("hasPermission(filterObject, read) or hasPermission(filterObject, admin)")
   List<Registration> list(Map params) {
      Registration.list params
   }

   @PostFilter("hasPermission(filterObject, read) or hasPermission(filterObject, admin)")
   int count(Map params) {
      Registration.count params
   }

   @Transactional
   @PreAuthorize("hasPermission(#registration, write) or hasPermission(#registration, admin)")
   void update(Registration registration, Map params) {
      registration.properties = params
   }

   @Transactional
   @PreAuthorize("hasPermission(#registration, delete) or hasPermission(#registration, admin)")
   void delete(Registration registration) {
      registration.delete()

      // Delete the ACL information as well
      aclUtilService.deleteAcl registration
   }
}
