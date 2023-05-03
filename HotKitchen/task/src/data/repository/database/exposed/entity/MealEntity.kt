package hotkitchen.data.repository.database.exposed.entity

import hotkitchen.data.repository.database.exposed.table.MealTable
import hotkitchen.domain.model.Meal
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MealEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MealEntity>(MealTable)

    var title: String by MealTable.title
    var price: Double by MealTable.price
    var imageUrl: String by MealTable.imageUrl
    var categories: List<Int> by MealTable.categories.transform(
        toColumn = { it.joinToString(" ") },
        toReal = { it.split(' ').map(String::toInt)}
    )


    fun toMeal(): Meal {
        return Meal(
            id.value,
            title,
            price,
            imageUrl,
            categories
        )
    }
}