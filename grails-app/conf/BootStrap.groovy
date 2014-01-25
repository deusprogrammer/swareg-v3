import com.swag.registration.SetupUpdaterService
import com.swag.registration.domain.ConfigHolder
import com.swag.registration.security.Role
import com.swag.registration.security.User
import com.swag.registration.security.UserRole
import grails.plugins.springsecurity.SpringSecurityService

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder as SCH

class BootStrap {
   def grailsApplication

   SpringSecurityService springSecurityService
   SetupUpdaterService setupUpdaterService

   def sessionFactory

   def init = { servletContext ->
       if (!ConfigHolder.getSwitch("swareg.setup")) {
           ConfigHolder.setSwitch("payPal.debug", false)
           ConfigHolder.setSwitch("swareg.setup", false)
       }
	   
	   String emailAddress = ConfigHolder.getConfig("email.address")
	   String emailPassword = ConfigHolder.getConfig("email.password")
	   
	   setupUpdaterService.updateEmail(emailAddress, emailPassword)

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