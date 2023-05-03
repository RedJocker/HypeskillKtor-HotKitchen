package hotkitchen.presentation.routing.responseDto

import hotkitchen.domain.model.Meal

data class GetAllMealResponse(private val meals: List<Meal>) : List<Meal> by meals, ApiResponse