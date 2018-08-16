package net.dontdrinkandroot.example.angularrestspringsecurity.fixtures;

import net.dontdrinkandroot.fixtures.Fixture;
import net.dontdrinkandroot.fixtures.loader.DefaultFixtureLoader;
import net.dontdrinkandroot.fixtures.purger.DatabasePurger;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AutowiringFixtureLoader extends DefaultFixtureLoader
{
    private AutowireCapableBeanFactory beanFactory;

    public AutowiringFixtureLoader(DatabasePurger databasePurger, AutowireCapableBeanFactory beanFactory)
    {
        super(databasePurger);
        this.beanFactory = beanFactory;
    }

    @Override
    protected Fixture instantiateFixtureClass(Class<? extends Fixture> fixtureClass)
    {
        return (Fixture) this.beanFactory.autowire(
                fixtureClass,
                AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR,
                false
        );
    }
}
