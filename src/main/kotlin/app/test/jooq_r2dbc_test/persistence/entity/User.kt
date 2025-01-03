package app.test.jooq_r2dbc_test.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("tbl_user")
class User(
    @Column("username")
    var username: String,
    @Column("password")
    var password: String,
    @Column("name")
    var name: String,
    @Column("age")
    var age: Int
) {
    @Id
    @Column("id")
    lateinit var id: String
    @Column("created_date")
    lateinit var createdDate: LocalDateTime
    @Column("updated_date")
    lateinit var updatedDate: LocalDateTime
}