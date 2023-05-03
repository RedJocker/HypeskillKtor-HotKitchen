package hotkitchen.presentation.routing.responseDto

import hotkitchen.domain.model.Order
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderResponse (
    val orderId: Int,
    val userEmail: String,
    val mealsIds: List<Int>,
    val price: Double,
    val address: String,
    val status: String,
): ApiResponse {
    companion object {
        fun fromOrder(order: Order): CreateOrderResponse {
            return CreateOrderResponse(
                order.orderId,
                order.userEmail,
                order.mealsIds,
                order.price,
                order.address,
                order.status
            )
        }
    }
}