package hotkitchen.presentation.routing.responseDto

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(val token: String = "abc.def.***") : ApiResponse