package app.test.jooq_r2dbc_test

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JooqR2dbcTestApplication

fun main(args: Array<String>) {
    runApplication<JooqR2dbcTestApplication>(*args)
}
