package hotkitchen.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.EnumSet

@Serializable
data class User (
    val email: String,
    val type: Type,
    val password: String,
) {
    @Serializable
    @Suppress("unused")
    enum class Type(val typeString: String) {
        @SerialName("staff") STAFF("staff"),
        @SerialName("testUser") TEST_USER("testUser"),
        @SerialName("client") CLIENT("client"),
        @SerialName("newType") NEW_TYPE("newType"),
        ;

        companion object {
            private val allValues = EnumSet.allOf(Type::class.java)

            fun fromTypeString(typeString: String): Type? {
                return allValues.firstOrNull { it.typeString == typeString }
            }
        }
    }
}
