package net.dontdrinkandroot.example.angularrestspringsecurity.config

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User
import net.dontdrinkandroot.example.angularrestspringsecurity.security.SecurityService
import net.dontdrinkandroot.fixtures.purger.DatabasePurger
import net.dontdrinkandroot.fixtures.purger.NoopDatabasePurger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Configuration
@EnableJpaAuditing
class BaseConfiguration {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun databasePurger(): DatabasePurger = NoopDatabasePurger()

    @Bean
    fun auditorAware(securityService: SecurityService): AuditorAware<User> =
            AuditorAware<User> { Optional.ofNullable(securityService.getCurrentUser()) }

//    @Bean
//    fun customSerializationModule() = object : SimpleModule() {
//        override fun setupModule(context: SetupContext) {
//            val serializers = SimpleSerializers()
//            val deserializers = SimpleDeserializers()
//
//            serializers.addSerializer(BlogPost::class.java, ExampleSerializer())
//
//            context.addSerializers(serializers)
//            context.addDeserializers(deserializers)
//        }
//    }
}
