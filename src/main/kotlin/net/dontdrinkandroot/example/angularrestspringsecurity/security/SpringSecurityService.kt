package net.dontdrinkandroot.example.angularrestspringsecurity.security

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Service
class SpringSecurityService(private val userDetailsService: JpaUserDetailsService) : SecurityService {
    override fun getCurrentUser(): User? {
        val principal = SecurityContextHolder.getContext().authentication?.principal
        return if (principal is User) principal else null
    }

    override fun setCurrentUser(user: User?) {
        if (null == user) {
            SecurityContextHolder.getContext().authentication = null
            return
        }
        val userDetails = userDetailsService.convertToUserDetails(user)
        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
    }
}
