package hotkitchen.data.repository.database.exposed.table

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object UserInfoTable : IntIdTable()  {
    val name: Column<String> = varchar("name", 100)
    val phone: Column<String> = varchar("phone", 20)
    val address: Column<String> = varchar("address", 300)
    val user: Column<EntityID<Int>> = reference("user", UserCredentialsTable).uniqueIndex()
}