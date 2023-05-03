package hotkitchen.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Category (
    val categoryId: Int,
    val title: String,
    val description: String
)