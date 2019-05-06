package com.football.topforecaster

import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.testcontainers.containers.JdbcDatabaseContainer
import org.testcontainers.containers.PostgreSQLContainerProvider


@RunWith(SpringRunner::class)
@SpringBootTest
@ContextConfiguration(
        initializers = [PostgresIT.Initializer::class]
)
abstract class PostgresIT {

    companion object {
        val POSTGRES_CONTAINER: JdbcDatabaseContainer<out JdbcDatabaseContainer<*>> =
                PostgreSQLContainerProvider().newInstance("9.6-alpine")

        init {
            POSTGRES_CONTAINER.start()
        }
    }

    class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

        override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
            TestPropertyValues
                    .of("spring.datasource.url=" + POSTGRES_CONTAINER.getJdbcUrl())
                    .and("spring.datasource.username=" + POSTGRES_CONTAINER.getUsername())
                    .and("spring.datasource.password=" + POSTGRES_CONTAINER.getPassword())
                    .applyTo(configurableApplicationContext.environment)
        }
    }
}
