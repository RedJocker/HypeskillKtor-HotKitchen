package hotkitchen.data.repository

import hotkitchen.data.repository.database.MealDatabase
import hotkitchen.domain.exception.DuplicateCategoryException
import hotkitchen.domain.exception.DuplicateMealException
import hotkitchen.domain.exception.InvalidCategoryIdException
import hotkitchen.domain.exception.InvalidMealIdException
import hotkitchen.domain.model.Category
import hotkitchen.domain.model.Meal
import hotkitchen.presentation.routing.requestDto.AddCategoryRequest
import hotkitchen.presentation.routing.requestDto.AddMealRequest

class MealRepository(private val mealDatabase: MealDatabase) {
    fun addMeal(addMealRequest: AddMealRequest): Meal? {

        val mealId = addMealRequest.mealId.toIntOrNull() ?: throw InvalidMealIdException()

//        val maybeMeal = mealDatabase.getMeal(mealId)
//
//        if(maybeMeal != null) {
//            throw DuplicateMealException()
//        }

        val containMeals = mealDatabase.containMeal(mealId)
        if(containMeals) {
            throw DuplicateMealException()
        }

        return mealDatabase.addMeal(
            mealId,
            addMealRequest.title,
            addMealRequest.price,
            addMealRequest.imageUrl,
            addMealRequest.categoryIds
        )
    }

    fun addCategory(addCategoryRequest: AddCategoryRequest): Category? {

        val maybeCategory = mealDatabase.getCategory(addCategoryRequest.categoryId)

        if(maybeCategory != null) {
            throw DuplicateCategoryException()
        }

        return mealDatabase.addCategory(
            addCategoryRequest.categoryId,
            addCategoryRequest.title,
            addCategoryRequest.description
        )
    }

    fun getMeal(mealId: String): Meal? {
        val mealIdInt = mealId.toIntOrNull() ?: throw InvalidMealIdException()
        return mealDatabase.getMeal(mealIdInt)
    }

    fun getCategory(categoryId: String): Category? {
        val categoryIdInt = categoryId.toIntOrNull() ?: throw InvalidCategoryIdException()
        return mealDatabase.getCategory(categoryIdInt)
    }

    fun getMealList(): List<Meal> {
        return mealDatabase.getMealList()
    }

    fun getCategoryList(): List<Category> {
        return mealDatabase.getCategoryList()
    }
}