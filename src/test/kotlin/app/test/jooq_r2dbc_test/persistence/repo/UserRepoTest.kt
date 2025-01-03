package app.test.jooq_r2dbc_test.persistence.repo

import app.test.jooq_r2dbc_test.config.R2dbcConfig
import app.test.jooq_r2dbc_test.persistence.entity.User
import kotlinx.coroutines.reactive.awaitLast
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.test.runTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.reactive.TransactionalOperator
import java.util.*
import kotlin.test.Test
import kotlin.test.assertNotNull

@DataR2dbcTest
@ActiveProfiles("test")
@Import(R2dbcConfig::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
open class UserRepoTest {
    @Autowired
    lateinit var userJooqRepo: UserJooqRepo

    @Autowired
    lateinit var userRepo: UserRepo

    @Autowired
    lateinit var transactionalOperator: TransactionalOperator

    @Test
    fun `find user by id`() = runTest {
        // given
        val userId = UUID.fromString("94dc2e24-c8dc-11ef-a416-6b1f3be1b365")

        // when
        val user = userRepo.findById(userId).awaitSingle()

        // then
        assertNotNull(user)

        println(user)
    }

    @Test
    fun `find all users`() = runTest {
        // given
        // when
        val users = userRepo.findAll().awaitLast()

        // then
        println(users)
    }

    @Test
    open fun `find by nullable conditions`() = runTest {

        // given
        val name = "test"
        val age = 20

        // when
        val duration = transactionalOperator.execute { it ->
            it.setRollbackOnly()
            userRepo.save(
                User("test2", "1234", "test2", age)
            )
                .thenMany(userJooqRepo.findByNameAndAge(name, age))
                .doOnNext { println("${it.name}, ${it.age}") }
        }
            // then
            .awaitLast()

//            .test()
//            .assertNext { assert(it.name == name) }
//            .assertNext { assert(it.age == age) }
//            .verifyError()
    }
}