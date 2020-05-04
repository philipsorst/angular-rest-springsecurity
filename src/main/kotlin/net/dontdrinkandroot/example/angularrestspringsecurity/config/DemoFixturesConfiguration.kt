package net.dontdrinkandroot.example.angularrestspringsecurity.config

import net.dontdrinkandroot.example.angularrestspringsecurity.fixtures.ContextRefreshFixtureLoader
import net.dontdrinkandroot.example.angularrestspringsecurity.fixtures.demo.BlogPosts
import net.dontdrinkandroot.example.angularrestspringsecurity.fixtures.demo.UserAdmin
import net.dontdrinkandroot.example.angularrestspringsecurity.fixtures.demo.UserDefault
import net.dontdrinkandroot.fixtures.loader.FixtureLoader
import net.dontdrinkandroot.fixtures.purger.DatabasePurger
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Configuration
@Profile("demofixtures")
class DemoFixturesConfiguration {

    @Bean
    fun fixtureLoader(purger: DatabasePurger, beanFactory: AutowireCapableBeanFactory): FixtureLoader =
            ContextRefreshFixtureLoader(purger, beanFactory, listOf(
                    UserAdmin::class.java,
                    UserDefault::class.java,
                    BlogPosts::class.java
            ))
}