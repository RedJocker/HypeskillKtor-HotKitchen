package hotkitchen.data.repository.database.exposed.table

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object CategoryTable: IdTable<Int>() {
    override val id: Column<EntityID<Int>> = integer("category_id").uniqueIndex().entityId()
    val title: Column<String> = varchar("title", 200)
    val description: Column<String> = varchar("description", 1000)
}