package net.dontdrinkandroot.example.angularrestspringsecurity.security

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User
import net.dontdrinkandroot.example.angularrestspringsecurity.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Service
class JpaUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
                ?: throw UsernameNotFoundException("The given user was not found")

        return convertToUserDetails(user)
    }

    fun convertToUserDetails(user: User): UserDetails =
            object : UserDetails {

                override fun getAuthorities(): Collection<GrantedAuthority> = user.roles.map { GrantedAuthority { it } }

                override fun getUsername(): String = user.username!!

                override fun getPassword(): String = user.password!!

                override fun isEnabled(): Boolean = true

                override fun isCredentialsNonExpired() = true

                override fun isAccountNonExpired(): Boolean = true

                override fun isAccountNonLocked(): Boolean = true
            }
}
