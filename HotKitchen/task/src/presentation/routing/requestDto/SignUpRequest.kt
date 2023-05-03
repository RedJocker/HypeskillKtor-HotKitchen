package hotkitchen.presentation.routing.requestDto

import hotkitchen.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest (
    val email: String,
    val userType: User.Type,
    val password: String,
)