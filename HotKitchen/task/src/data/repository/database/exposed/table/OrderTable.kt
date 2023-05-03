package hotkitchen.data.repository.database.exposed.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object OrderTable: IntIdTable(columnName = "orderId") {
    val userEmail: Column<String> = varchar("user_email", 200)
    val mealIds: Column<String> = varchar("meals", 500)
    val price: Column<Double> = double("price")
    val address: Column<String> = varchar("address", 300)
    val status: Column<String> = varchar("status", 50)
}