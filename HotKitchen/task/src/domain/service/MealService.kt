package hotkitchen.domain.service

import hotkitchen.data.repository.MealRepository
import hotkitchen.domain.exception.DatabaseException
import hotkitchen.domain.model.Category
import hotkitchen.domain.model.Meal
import hotkitchen.presentation.routing.requestDto.AddCategoryRequest
import hotkitchen.presentation.routing.requestDto.AddMealRequest
import hotkitchen.presentation.routing.responseDto.*

class MealService(private val mealRepository: MealRepository) {
    fun addMeal(addMealRequest: AddMealRequest): ApiResponse {
        val meal: Meal = mealRepository.addMeal(addMealRequest) ?: throw DatabaseException()

        return AddMealResponse(meal)
    }

    fun addCategory(addCategoryRequest: AddCategoryRequest): ApiResponse {

        val category: Category = mealRepository.addCategory(addCategoryRequest) ?: throw DatabaseException()

        return AddCategoryResponse(category)
    }

    fun getMeal(categoryId: String): ApiResponse {

        val meal: Meal = mealRepository.getMeal(categoryId) ?: throw DatabaseException()

        return GetMealResponse.fromMeal(meal)
    }

    fun getMealList(): ApiResponse {
        return GetAllMealResponse(mealRepository.getMealList())
    }

    fun getCategory(categoryId: String): ApiResponse {

        val category: Category = mealRepository.getCategory(categoryId) ?: throw DatabaseException()

        return GetCategoryResponse.fromCategory(category)
    }

    fun getListCategory(): ApiResponse {
        return GetAllCategoryResponse(mealRepository.getCategoryList())
    }
}