package hotkitchen.presentation.routing.requestDto

import kotlinx.serialization.Serializable

@Serializable
data class AddCategoryRequest (
    val categoryId: Int,
    val title: String,
    val description: String
)