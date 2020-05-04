package net.dontdrinkandroot.example.angularrestspringsecurity.fixtures.demo

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User
import net.dontdrinkandroot.fixtures.Fixture
import net.dontdrinkandroot.fixtures.referencerepository.ReferenceRepository
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.EntityManager

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
class UserAdmin(private val passwordEncoder: PasswordEncoder) : Fixture {

    override fun load(entityManager: EntityManager, referenceRepository: ReferenceRepository) {
        val user = User()
        user.username = "admin"
        user.firstName = "Admin"
        user.lastName = "User"
        user.password = passwordEncoder.encode(user.username)
        user.roles = setOf("ROLE_ADMIN", "ROLE_USER")
        entityManager.persist(user)
        referenceRepository.store("user-0", user)
    }
}
