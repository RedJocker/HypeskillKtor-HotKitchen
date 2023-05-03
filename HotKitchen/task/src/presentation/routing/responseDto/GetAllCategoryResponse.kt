package hotkitchen.presentation.routing.responseDto

import hotkitchen.domain.model.Category

data class GetAllCategoryResponse(private val categories: List<Category>) : List<Category> by categories, ApiResponse