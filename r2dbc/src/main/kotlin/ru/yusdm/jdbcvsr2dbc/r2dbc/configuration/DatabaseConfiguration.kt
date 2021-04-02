package ru.yusdm.jdbcvsr2dbc.r2dbc.configuration


import io.r2dbc.pool.PoolingConnectionFactoryProvider
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.R2dbcTransactionManager

/*
@Configuration
@EnableR2dbcRepositories
internal class DatabaseConfiguration : AbstractR2dbcConfiguration() {

    @Bean
    override fun connectionFactory(): ConnectionFactory {
        return ConnectionFactories.get(
            ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "pool")
                .option(ConnectionFactoryOptions.PROTOCOL, "postgresql")
                .option(ConnectionFactoryOptions.HOST, "localhost")
                .option(ConnectionFactoryOptions.PORT, 5434)
                .option(ConnectionFactoryOptions.USER, "test")
                .option(ConnectionFactoryOptions.PASSWORD, "111")
                .option(ConnectionFactoryOptions.DATABASE,  "jdbc_vs_r2dbc")
                .build()
        )
    }

    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory): R2dbcTransactionManager {
        return  R2dbcTransactionManager(connectionFactory)
    }

}*/