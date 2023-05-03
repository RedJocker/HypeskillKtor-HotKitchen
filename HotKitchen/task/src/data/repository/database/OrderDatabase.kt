package hotkitchen.data.repository.database

import hotkitchen.domain.model.Order

interface OrderDatabase {
    fun createOrderRequest(mealsIds: List<Int>, email: String): Order?
    fun allOrders(): List<Order>
}
