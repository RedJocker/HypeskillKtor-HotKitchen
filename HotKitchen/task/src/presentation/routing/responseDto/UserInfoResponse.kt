package hotkitchen.presentation.routing.responseDto

import hotkitchen.domain.model.User
import hotkitchen.domain.model.UserInfo
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    val email: String,
    val userType: User.Type,
    val name: String,
    val phone: String,
    val address: String
): ApiResponse {
    companion object {
        fun fromUserInfo(userInfo: UserInfo): UserInfoResponse {
            return UserInfoResponse(
                userInfo.email,
                userInfo.type,
                userInfo.name,
                userInfo.phone,
                userInfo.address
            )
        }
    }
}