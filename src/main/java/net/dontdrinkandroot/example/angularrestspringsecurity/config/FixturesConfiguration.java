package net.dontdrinkandroot.example.angularrestspringsecurity.config;

import net.dontdrinkandroot.example.angularrestspringsecurity.fixtures.BlogPosts;
import net.dontdrinkandroot.example.angularrestspringsecurity.fixtures.ContextRefreshedFixtureLoader;
import net.dontdrinkandroot.fixtures.loader.FixtureLoader;
import net.dontdrinkandroot.fixtures.purger.DatabasePurger;
import net.dontdrinkandroot.fixtures.purger.MetamodelDatabasePurger;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class FixturesConfiguration
{
    @Bean
    public FixtureLoader fixtureLoader(DatabasePurger purger, AutowireCapableBeanFactory beanFactory)
    {
        return new ContextRefreshedFixtureLoader(purger, beanFactory, Arrays.asList(
                BlogPosts.class
        ));
    }

    @Bean
    public DatabasePurger databasePurger()
    {
        return new MetamodelDatabasePurger();
    }
}
