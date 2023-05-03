package hotkitchen.data.repository.database.exposed.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object UserCredentialsTable : IntIdTable() {
    val email: Column<String> = varchar("email", 100).uniqueIndex()
    val password: Column<String> = varchar("password", 100)
    val userType: Column<String> = varchar("user_type", 50)
}