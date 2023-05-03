package hotkitchen.presentation.routing.requestDto

import kotlinx.serialization.Serializable

@Serializable
data class AddMealRequest(
    val mealId: String,
    val title: String,
    val price: Double,
    val imageUrl: String,
    val categoryIds: List<Int>
)