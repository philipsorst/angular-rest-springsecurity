package net.dontdrinkandroot.example.angularrestspringsecurity.fixtures

import net.dontdrinkandroot.fixtures.Fixture
import net.dontdrinkandroot.fixtures.loader.DefaultFixtureLoader
import net.dontdrinkandroot.fixtures.purger.DatabasePurger
import org.springframework.beans.factory.config.AutowireCapableBeanFactory

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
open class AutowiringFixtureLoader(
        databasePurger: DatabasePurger,
        private val beanFactory: AutowireCapableBeanFactory
) : DefaultFixtureLoader(databasePurger) {

    @Throws(InstantiationException::class, IllegalAccessException::class)
    override fun instantiateFixtureClass(fixtureClass: Class<out Fixture>): Fixture {
        return beanFactory.autowire(
                fixtureClass,
                AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR,
                false
        ) as Fixture
    }
}
