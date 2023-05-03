package hotkitchen.data.repository.database.exposed

import hotkitchen.data.repository.database.MealDatabase
import hotkitchen.data.repository.database.exposed.entity.CategoryEntity
import hotkitchen.data.repository.database.exposed.entity.MealEntity
import hotkitchen.data.repository.database.exposed.table.CategoryTable
import hotkitchen.data.repository.database.exposed.table.MealTable
import hotkitchen.domain.model.Category
import hotkitchen.domain.model.Meal
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedH2MealDatabase(private val database: Database): MealDatabase {
    override fun addMeal(
        mealId: Int,
        title: String,
        price: Double,
        imageUrl: String,
        categoryIds: List<Int>
    ): Meal? {
        return transaction(database) {
            val mealId = MealTable.insertAndGetId {
                it[id] = mealId
                it[MealTable.title] = title
                it[MealTable.price] = price
                it[MealTable.imageUrl] = imageUrl
                it[categories] = categoryIds.joinToString(" ")
            }


           MealEntity.find {
                MealTable.id eq mealId
            }.firstOrNull()?.toMeal()
        }
    }

    override fun getMeal(mealId: Int): Meal? {
        return transaction(database) {
            MealEntity.findById(mealId)
        }?.toMeal()
    }

    override fun getMealList(): List<Meal> {
        return transaction(database) {
            MealEntity.all().map(MealEntity::toMeal)
        }
    }

    override fun addCategory(categoryId: Int, title: String, description: String): Category? {
        return transaction(database) {
            val categoryId = CategoryTable.insertAndGetId {
                it[id] = categoryId
                it[CategoryTable.title] = title
                it[CategoryTable.description] = description
            }

            CategoryEntity.find {
                CategoryTable.id eq categoryId
            }.firstOrNull()?.toCategory()
        }
    }

    override fun getCategory(categoryId: Int): Category? {
        return transaction(database) {
            CategoryEntity.findById(categoryId)
        }?.toCategory()
    }

    override fun getCategoryList(): List<Category> {
        return transaction(database) {
            CategoryEntity.all().map(CategoryEntity::toCategory)
        }
    }

    override fun containMeals(mealIdList: List<Int>): Boolean {
        return transaction(database) {
            MealTable.select { MealTable.id inList mealIdList }.count() == mealIdList.size.toLong()
        }
    }

    override fun containMeal(mealId: Int): Boolean {
        return transaction(database) {
            MealTable.select { MealTable.id eq mealId }.count() == 1L
        }
    }
}