package net.dontdrinkandroot.example.angularrestspringsecurity.fixtures;

import net.dontdrinkandroot.fixtures.Fixture;
import net.dontdrinkandroot.fixtures.purger.DatabasePurger;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class ContextRefreshedFixtureLoader extends AutowiringFixtureLoader implements ApplicationListener<ContextRefreshedEvent>
{
    private Collection<Class<? extends Fixture>> fixtureClasses = new ArrayList<>();

    public ContextRefreshedFixtureLoader(
            DatabasePurger databasePurger,
            AutowireCapableBeanFactory beanFactory,
            Collection<Class<? extends Fixture>> fixtureClasses
    )
    {
        super(databasePurger, beanFactory);
        this.fixtureClasses = fixtureClasses;
    }

    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        this.load(this.fixtureClasses);
    }
}
