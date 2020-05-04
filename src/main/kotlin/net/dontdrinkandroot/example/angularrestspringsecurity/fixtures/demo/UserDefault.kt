package net.dontdrinkandroot.example.angularrestspringsecurity.fixtures.demo

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User
import net.dontdrinkandroot.fixtures.Fixture
import net.dontdrinkandroot.fixtures.referencerepository.ReferenceRepository
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.EntityManager

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
class UserDefault(private val passwordEncoder: PasswordEncoder) : Fixture {

    override fun load(entityManager: EntityManager, referenceRepository: ReferenceRepository) {
        val user = User()
        user.username = "user"
        user.firstName = "Default"
        user.lastName = "User"
        user.password = passwordEncoder.encode(user.username)
        user.roles = setOf("ROLE_ADMIN")
        entityManager.persist(user)
        referenceRepository.store("user-1", user)
    }
}
