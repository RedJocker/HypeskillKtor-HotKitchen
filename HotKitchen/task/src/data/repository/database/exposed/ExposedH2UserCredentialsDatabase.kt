package hotkitchen.data.repository.database.exposed

import hotkitchen.data.repository.database.UserCredentialsDatabase
import hotkitchen.data.repository.database.exposed.entity.UserCredentialsEntity
import hotkitchen.data.repository.database.exposed.table.UserCredentialsTable
import hotkitchen.domain.model.User
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedH2UserCredentialsDatabase(private val database: Database): UserCredentialsDatabase {
    override fun userByEmail(email: String): User? {
        return transaction(database) {
            UserCredentialsEntity.find {
                UserCredentialsTable.email eq email
            }.limit(1)
                .firstOrNull()
                ?.toUser()
        }
    }

    override fun addUser(email: String, type: User.Type, password: String): User? {
        return try {
            transaction(database) {
                UserCredentialsEntity.new {
                    this.email = email
                    this.userType = type.name
                    this.password = password
                }
            }.toUser()
        } catch (e: Exception) {
            null
        }
    }
}