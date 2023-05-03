package hotkitchen.data.repository.database.exposed

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedDatabaseConnection(private vararg val tables: Table) {
    val database = Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")

    init {
        transaction(database) {
            tables.forEach {
                SchemaUtils.create(it)
            }
            addLogger(StdOutSqlLogger)
        }
    }
}