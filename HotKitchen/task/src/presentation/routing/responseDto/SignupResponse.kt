package hotkitchen.presentation.routing.responseDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignupResponse(
    val status: Status
): ApiResponse {
    @Serializable
    enum class Status {
        @SerialName("Signed In") SIGNED_UP,
        @SerialName("Registration failed") REGISTRATION_FAILED,
        @SerialName("Invalid email") INVALID_EMAIL,
        @SerialName("Invalid password") INVALID_PASS,
        @SerialName("User already exists") DUPLICATED_USER
        ;
    }
}