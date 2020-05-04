package net.dontdrinkandroot.example.angularrestspringsecurity.config

import net.dontdrinkandroot.fixtures.purger.DatabasePurger
import net.dontdrinkandroot.fixtures.purger.MetamodelDatabasePurger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Configuration
@Profile("test", "purge")
class DatabasePurgerConfiguration {

    @Bean
    fun databasePurger(): DatabasePurger = MetamodelDatabasePurger()
}
