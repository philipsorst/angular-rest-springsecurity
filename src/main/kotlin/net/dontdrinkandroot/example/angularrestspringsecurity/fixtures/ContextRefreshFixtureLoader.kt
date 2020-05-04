package net.dontdrinkandroot.example.angularrestspringsecurity.fixtures

import net.dontdrinkandroot.fixtures.Fixture
import net.dontdrinkandroot.fixtures.purger.DatabasePurger
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.transaction.annotation.Transactional

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
open class ContextRefreshFixtureLoader(
        databasePurger: DatabasePurger,
        beanFactory: AutowireCapableBeanFactory,
        private val fixtureClasses: Collection<Class<out Fixture>>
) : AutowiringFixtureLoader(databasePurger, beanFactory), ApplicationListener<ContextRefreshedEvent> {

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        load(fixtureClasses)
    }
}
