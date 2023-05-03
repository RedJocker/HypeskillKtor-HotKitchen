package hotkitchen.presentation.routing.responseDto

import kotlinx.serialization.Serializable

@Serializable
data class SimpleStatusResponse (
    val status: String
) : ApiResponse