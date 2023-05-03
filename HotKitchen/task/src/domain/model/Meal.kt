package hotkitchen.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Meal (
    val mealId: Int,
    val title: String,
    val price: Double,
    val imageUrl: String,
    val categoryIds: List<Int>
)