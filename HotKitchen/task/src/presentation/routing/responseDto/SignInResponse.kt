package hotkitchen.presentation.routing.responseDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse (
    val status: Status
): ApiResponse {
    @Serializable
    enum class Status {
        @SerialName("Signed Up") SIGNED_IN,
        @SerialName("Authorization failed") AUTHORIZATION_FAILED,
        @SerialName("Invalid email or password") INVALID_SIGNIN,
        ;
    }
}