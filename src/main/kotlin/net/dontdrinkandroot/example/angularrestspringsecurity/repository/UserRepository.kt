package net.dontdrinkandroot.example.angularrestspringsecurity.repository

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@RepositoryRestResource
interface UserRepository : JpaRepository<User, Long> {

    @RestResource(exported = false)
    fun findByUsername(username: String): User?
}
