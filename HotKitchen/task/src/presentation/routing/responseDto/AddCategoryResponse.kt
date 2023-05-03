package hotkitchen.presentation.routing.responseDto

import hotkitchen.domain.model.Category
import kotlinx.serialization.Serializable

@Serializable
data class AddCategoryResponse(val category: Category) : ApiResponse