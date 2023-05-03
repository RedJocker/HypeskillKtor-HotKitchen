package hotkitchen.data.repository

import hotkitchen.data.repository.database.MealDatabase
import hotkitchen.data.repository.database.OrderDatabase
import hotkitchen.domain.exception.InvalidMealIdException
import hotkitchen.domain.model.Order

class OrderRepository(private val mealDatabase: MealDatabase, private val orderDatabase: OrderDatabase) {
    fun createOrderRequest(mealsIds: List<Int>, email: String): Order? {
        if (mealDatabase.containMeals(mealsIds).not()) {
            throw InvalidMealIdException("Order should contain only existing mealsIds")
        }

        return orderDatabase.createOrderRequest(mealsIds, email)
    }

    fun lastOrder(): Order? {
        return orderDatabase.allOrders().lastOrNull()
    }
}