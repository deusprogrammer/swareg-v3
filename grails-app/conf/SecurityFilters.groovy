import com.swag.registration.domain.ConfigHolder
import grails.plugins.springsecurity.SpringSecurityService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.authority.AuthorityUtils

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mmain
 */
class SecurityFilters {
	SpringSecurityService springSecurityService
    def filters = {
        everything(controller: "setupFlow|errors", invert: true) {
            before = {
				if (!ConfigHolder.getSwitch("swareg.setup")) {
					springSecurityService.reauthenticate("global_admin")
					redirect(controller: "setupFlow", action: "setup")
					return
				}
            }
        }
    }
}

