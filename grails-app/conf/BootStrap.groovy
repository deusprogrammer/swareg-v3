import com.swag.registration.domain.ConfigHolder
import com.swag.registration.security.Role
import com.swag.registration.security.User
import com.swag.registration.security.UserRole
import grails.plugins.springsecurity.SpringSecurityService
import static org.springframework.security.acls.domain.BasePermission.ADMINISTRATION
import static org.springframework.security.acls.domain.BasePermission.DELETE
import static org.springframework.security.acls.domain.BasePermission.READ
import static org.springframework.security.acls.domain.BasePermission.WRITE

import org.grails.plugins.springsecurity.service.acl.AclService
import org.grails.plugins.springsecurity.service.acl.AclUtilService
import org.springframework.security.authentication. UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder as SCH

class BootStrap {
    def grailsApplication

   AclService aclService
   AclUtilService aclUtilService
   SpringSecurityService springSecurityService

   def objectIdentityRetrievalStrategy
   def sessionFactory

   def init = { servletContext ->
       try {
           new ConfigHolder(configKey: "payPal.clientId", configValue: "").save()
           new ConfigHolder(configKey: "payPal.secret", configValue: "").save()
           new ConfigHolder(configKey: "payPal.debug", configValue: "false").save()
           new ConfigHolder(configKey: "swareg.setup", configValue: "false").save()
       } catch (Exception e) {
           println "ConfigHolder already initialized"
       }

       createUsers()
       loginAsAdmin()
       sessionFactory.currentSession.flush()

       // logout
       SCH.clearContext()
   }

   private void loginAsAdmin() {
       // have to be authenticated as an admin to create ACLs
       SCH.context.authentication = new UsernamePasswordAuthenticationToken(
                'global_admin', 'password',
                AuthorityUtils.createAuthorityList('ROLE_GLOBAL'))
   }

   private void createUsers() {
       Role roleGlobal = new Role(authority: 'ROLE_GLOBAL').save()
       Role roleAdmin  = new Role(authority: 'ROLE_ADMIN').save()
       Role roleOps    = new Role(authority: 'ROLE_OPS').save()
       Role roleUser   = new Role(authority: 'ROLE_USER').save()

       def admin = new User(username: 'global_admin', enabled: true, password: 'password', emailAddress: 'god@god.com').save()

       UserRole.create admin, roleGlobal, true
   }
}