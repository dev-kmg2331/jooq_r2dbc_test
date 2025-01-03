package app.test.jooq_r2dbc_test.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.transaction.reactive.TransactionalOperator

@Configuration
@ComponentScan(basePackages = ["app.test.jooq_r2dbc_test.*"])
class R2dbcConfig {
    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory): R2dbcTransactionManager {
        return R2dbcTransactionManager(connectionFactory)
    }

    @Bean
    fun transactionalOperator(transactionManager: R2dbcTransactionManager): TransactionalOperator {
        return TransactionalOperator.create(transactionManager)
    }
}