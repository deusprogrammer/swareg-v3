import com.swag.registration.domain.ConfigHolder
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
    def sessionService
    def filters = {
        everything(controller: "setupFlow|errors", action: "setup", invert: true) {
            before = {
				if (!ConfigHolder.getSwitch("swareg.setup")) {
					SecurityContextHolder.context.authentication = new UsernamePasswordAuthenticationToken(
						'global_admin', 
						'sethR4chan',
						AuthorityUtils.createAuthorityList('ROLE_GLOBAL'))
					redirect(controller: "setupFlow", action: "setup")
				}
            }
        }
    }
}

