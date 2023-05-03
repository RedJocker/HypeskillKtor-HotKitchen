package hotkitchen.presentation.routing.responseDto

import hotkitchen.domain.model.Category
import kotlinx.serialization.Serializable

@Serializable
data class GetCategoryResponse (
    val categoryId: Int,
    val title: String,
    val description: String
): ApiResponse {
    companion object {
        fun fromCategory(category: Category): GetCategoryResponse {
            return GetCategoryResponse(
                categoryId = category.categoryId,
                title = category.title,
                description = category.description
            )
        }
    }
}