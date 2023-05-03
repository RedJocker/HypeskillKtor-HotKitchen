package hotkitchen.data.repository.database.exposed.entity

import hotkitchen.data.repository.database.exposed.table.OrderTable
import hotkitchen.domain.model.Order
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class OrderEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<OrderEntity>(OrderTable)

    var userEmail: String by OrderTable.userEmail
    var mealsIds: List<Int> by OrderTable.mealIds.transform(
        toColumn = { it.joinToString(" ") },
        toReal = { it.split(' ').map(String::toInt)}
    )
    var price: Double by OrderTable.price
    var address: String by OrderTable.address
    var status: String by OrderTable.status

    fun toOrder(): Order {
        return Order(
            id.value,
            userEmail,
            mealsIds,
            price,
            address,
            status,
        )
    }
}