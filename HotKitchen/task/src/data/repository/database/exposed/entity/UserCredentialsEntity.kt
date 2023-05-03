package hotkitchen.data.repository.database.exposed.entity

import hotkitchen.data.repository.database.exposed.table.UserCredentialsTable
import hotkitchen.domain.model.User
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserCredentialsEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserCredentialsEntity>(UserCredentialsTable)

    var email: String by UserCredentialsTable.email
    var password: String by UserCredentialsTable.password
    var userType: String by UserCredentialsTable.userType

    fun toUser(): User {
        return User(email, User.Type.valueOf(userType), password)
    }
}