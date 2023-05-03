package hotkitchen.data.repository.database.exposed.table

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object MealTable: IdTable<Int>() {
    override val id: Column<EntityID<Int>> = integer("meal_id").uniqueIndex().entityId()
    val title: Column<String> = varchar("title", 200)
    val price: Column<Double> = double("price")
    val imageUrl: Column<String> =  varchar("image_url", 300)
    val categories: Column<String> = varchar("categories" ,500)
}