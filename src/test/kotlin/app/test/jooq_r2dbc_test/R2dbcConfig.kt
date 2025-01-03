package app.test.jooq_r2dbc_test

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.reactive.TransactionalOperator

@TestConfiguration
class R2dbcConfig {
//    @Bean
//    @Qualifier("postgresConnectionFactory")
//    fun mySQLConnectionFactory(): ConnectionFactory {
//        return PostgresqlConnectionFactory(
//            PostgresqlConnectionConfiguration.builder()
//                .host("125.138.98.82")
//                .port(5432)
//                .username("postgres")
//                .password("oms20190211")
//                .database("oms")
//                .schema("oms_cloud")
//                .build()
//        )
//    }

//    @Bean(name = ["r2dbcTransactionManager"])
//    fun reactiveTransactionManager(connectionFactory: ConnectionFactory): ReactiveTransactionManager {
//        return R2dbcTransactionManager(connectionFactory)
//    }

    @Bean(name = ["testTransactionalOperator"])
    fun transactionalOperator(reactiveTransactionManager: ReactiveTransactionManager): TransactionalOperator {
        return TransactionalOperator.create(reactiveTransactionManager)
    }
}