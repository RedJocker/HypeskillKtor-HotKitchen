package hotkitchen.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val email: String,
    val type: User.Type,
    val name: String,
    val phone: String,
    val address: String
)