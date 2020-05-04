package net.dontdrinkandroot.example.angularrestspringsecurity.config

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.BlogPost
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User
import net.dontdrinkandroot.example.angularrestspringsecurity.repository.BlogPostRepository
import net.dontdrinkandroot.example.angularrestspringsecurity.repository.UserRepository
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Configuration
class RestConfiguration : RepositoryRestConfigurer {

    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
        config.withEntityLookup()
                .forRepository(UserRepository::class.java, User::username, UserRepository::findByUsername)
                .forRepository(BlogPostRepository::class.java, BlogPost::slug, BlogPostRepository::findBySlug)
    }
}
