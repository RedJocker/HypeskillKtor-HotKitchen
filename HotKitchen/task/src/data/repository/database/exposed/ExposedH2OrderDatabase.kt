package data.repository.database.exposed

import hotkitchen.data.repository.database.OrderDatabase
import hotkitchen.data.repository.database.exposed.entity.MealEntity
import hotkitchen.data.repository.database.exposed.entity.OrderEntity
import hotkitchen.data.repository.database.exposed.table.MealTable
import hotkitchen.data.repository.database.exposed.table.UserCredentialsTable
import hotkitchen.data.repository.database.exposed.table.UserInfoTable
import hotkitchen.domain.exception.UserInfoNotFoundException
import hotkitchen.domain.model.Order
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedH2OrderDatabase(private val database: Database) : OrderDatabase {
    override fun createOrderRequest(mealsIds: List<Int>, email: String): Order {
        return transaction(database) {
            val address = UserInfoTable.innerJoin(UserCredentialsTable).slice(UserInfoTable.address).select {
                UserCredentialsTable.email eq email
            }
                .limit(1)
                .map { it[UserInfoTable.address] }
                .firstOrNull()
                ?: throw UserInfoNotFoundException()

            val meals = MealEntity.find {
                MealTable.id inList mealsIds
            }

            val price = meals.sumOf { it.price }

            OrderEntity.new {
                this.userEmail = email
                this.mealsIds = mealsIds
                this.price = price
                this.address = address
                this.status = Order.defaultCreationStatus
            }.toOrder()
        }
    }

    override fun allOrders(): List<Order> {
        return transaction(database) {
            OrderEntity.all()
                .map(OrderEntity::toOrder)
        }
    }
}