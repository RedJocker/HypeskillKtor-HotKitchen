package hotkitchen.presentation.routing.requestDto

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest (
    val email: String,
    val password: String,
)