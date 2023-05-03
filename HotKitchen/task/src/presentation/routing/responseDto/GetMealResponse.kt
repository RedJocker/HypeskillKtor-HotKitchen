package hotkitchen.presentation.routing.responseDto

import hotkitchen.domain.model.Meal
import kotlinx.serialization.Serializable

@Serializable
data class GetMealResponse (
    val mealId: Int,
    val title: String,
    val price: Double,
    val imageUrl: String,
    val categoryIds: List<Int>
): ApiResponse {
    companion object {
        fun fromMeal(meal: Meal): GetMealResponse {
            return GetMealResponse(
                 mealId = meal.mealId,
                 title = meal.title,
                 price = meal.price,
                 imageUrl = meal.imageUrl,
                 categoryIds = meal.categoryIds
            )
        }
    }
}