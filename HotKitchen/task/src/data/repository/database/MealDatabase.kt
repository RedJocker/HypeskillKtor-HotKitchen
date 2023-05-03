package hotkitchen.data.repository.database

import hotkitchen.domain.model.Category
import hotkitchen.domain.model.Meal

interface MealDatabase {
    fun addMeal(mealId: Int, title: String, price: Double, imageUrl: String, categoryIds: List<Int>): Meal?
    fun getMeal(mealId: Int): Meal?
    fun addCategory(categoryId: Int, title: String, description: String): Category?
    fun getCategory(categoryId: Int): Category?
    fun getMealList(): List<Meal>
    fun getCategoryList(): List<Category>
    fun containMeals(mealIdList: List<Int>): Boolean
    fun containMeal(mealId: Int): Boolean
}