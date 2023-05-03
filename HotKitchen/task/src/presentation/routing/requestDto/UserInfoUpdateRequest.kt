package hotkitchen.presentation.routing.requestDto

import hotkitchen.domain.model.User
import kotlinx.serialization.Serializable


@Serializable
data class UserInfoUpdateRequest (
    val name: String,
    val userType: User.Type,
    val phone: String,
    val email: String,
    val address: String,
)