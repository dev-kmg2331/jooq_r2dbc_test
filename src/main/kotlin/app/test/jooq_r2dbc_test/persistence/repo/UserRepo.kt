package app.test.jooq_r2dbc_test.persistence.repo

import app.test.jooq_r2dbc_test.persistence.entity.User
import io.r2dbc.spi.ConnectionFactory
import org.jooq.Record
import org.jooq.TableField
import org.jooq.generated.tables.references.TBL_USER
import org.jooq.impl.DSL
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.*

interface UserRepo : ReactiveCrudRepository<User, UUID>

interface BaseJooqRepo {

}

interface UserJooqRepo : BaseJooqRepo {
    fun findByNameAndAge(name: String?, age: Int?): Flux<User>
}

@Repository
class UserRepoImpl(connectionFactory: ConnectionFactory) : UserJooqRepo {
    private val dsl = DSL.using(connectionFactory)

    override fun findByNameAndAge(name: String?, age: Int?): Flux<User> = Flux.from(
        dsl.select(TBL_USER)
            .from(TBL_USER)
            .where(
                name?.let { TBL_USER.NAME.contains(it) } ?: DSL.noCondition(),
                age?.let { TBL_USER.AGE.eq(it) } ?: DSL.noCondition()
            )
    ).map { r -> r.into(User::class.java) }

    fun inIfNotEmpty(columnVal: TableField<Record, Int>, paramVal: Any) {

    }
}
