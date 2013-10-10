package com.swag.registration.security.acl

import java.util.Map

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.security.acls.model.Permission
import org.springframework.transaction.annotation.Transactional

import com.swag.registration.domain.Ownable

class PermissionService {
    def aclPermissionFactory
    def aclService
    def aclUtilService
    def springSecurityService

   void addPermission(Ownable ownable, String username, int permission) {
      addPermission ownable, username, aclPermissionFactory.buildFromMask(permission)
   }

   @PreAuthorize("hasPermission(#ownable, admin)")
   @Transactional
   void addPermission(Ownable ownable, String username, Permission permission) {
      aclUtilService.addPermission ownable, username, permission
   }

   @Transactional
   @PreAuthorize("hasPermission(#ownable, admin)")
   void deletePermission(Ownable ownable, String username, Permission permission) {
      def acl = aclUtilService.readAcl(ownable)

      // Remove all permissions associated with this particular
      // recipient (string equality to KISS)
      acl.entries.eachWithIndex { entry, i ->
         if (entry.sid.equals(recipient) && entry.permission.equals(permission)) {
            acl.deleteAce i
         }
      }

      aclService.updateAcl acl
   }
}
