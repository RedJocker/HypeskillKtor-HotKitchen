package hotkitchen.data.repository.database.exposed.table

import org.jetbrains.exposed.sql.Table

object MealToCategoryRelationTable: Table() {
    val mealId = reference("meal_id", MealTable)
    val categoryId = reference("category_id", CategoryTable)
}