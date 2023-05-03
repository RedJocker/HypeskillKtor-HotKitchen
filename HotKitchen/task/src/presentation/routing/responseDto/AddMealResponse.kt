package hotkitchen.presentation.routing.responseDto

import hotkitchen.domain.model.Meal
import kotlinx.serialization.Serializable

@Serializable
data class AddMealResponse(val meal: Meal) : ApiResponse