package net.dontdrinkandroot.example.angularrestspringsecurity.fixtures.demo

import com.github.javafaker.Faker
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User
import net.dontdrinkandroot.fixtures.Fixture
import net.dontdrinkandroot.fixtures.referencerepository.ReferenceRepository
import org.apache.commons.lang3.StringUtils
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.EntityManager

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
class Users(val passwordEncoder: PasswordEncoder) : Fixture {

    override fun load(entityManager: EntityManager, referenceRepository: ReferenceRepository) {
        val faker = Faker()
        for (i in 2..10) {
            val user = User()
            user.firstName = faker.name().firstName()
            user.lastName = faker.name().lastName()
            user.username = this.createUserName(user.firstName!!, user.lastName!!)
            user.password = passwordEncoder.encode(user.username)
            user.roles = setOf("ROLE_USER")
            entityManager.persist(user)
            referenceRepository.store(referenceName(i), user)
        }
    }

    private fun createUserName(firstName: String, lastName: String): String =
            StringUtils.deleteWhitespace(
                    firstName.replace("'".toRegex(), "").toLowerCase()
                            + "."
                            + lastName.replace("'".toRegex(), "").toLowerCase()
            )

    companion object {
        fun referenceName(i: Int) = "user-$i"
    }
}
