package ru.yusdm.jdbcvsr2dbc.coroutines.configuration

import com.zaxxer.hikari.HikariDataSource
import liquibase.Contexts
import liquibase.LabelExpression
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct


@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(LiquibaseProperties::class)
class LiquibaseConfiguration(private val liquibaseProperties: LiquibaseProperties) {

    @PostConstruct
    fun applyChangelog() {
        HikariDataSource().apply {
            this.driverClassName = liquibaseProperties.driverClassName
            this.username = liquibaseProperties.user
            this.password = liquibaseProperties.password
            this.jdbcUrl = liquibaseProperties.url
        }.connection.use {

            val liquibase = Liquibase(
                liquibaseProperties.changeLog,
                ClassLoaderResourceAccessor(),
                DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(
                        JdbcConnection(it)
                    )
            )

            liquibase.update(Contexts(), LabelExpression())
        }
    }
}