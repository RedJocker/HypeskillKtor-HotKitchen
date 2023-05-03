package hotkitchen.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Order (
    val orderId: Int,
    val userEmail: String,
    val mealsIds: List<Int>,
    val price: Double,
    val address: String,
    val status: String,
) {
    companion object {
        const val defaultCreationStatus = "COOK"
    }
}
