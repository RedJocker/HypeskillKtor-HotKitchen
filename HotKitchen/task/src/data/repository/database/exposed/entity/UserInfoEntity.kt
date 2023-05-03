package hotkitchen.data.repository.database.exposed.entity

import hotkitchen.data.repository.database.exposed.table.UserInfoTable
import hotkitchen.domain.model.User
import hotkitchen.domain.model.UserInfo
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserInfoEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserInfoEntity>(UserInfoTable)

    var name: String by UserInfoTable.name
    var phone: String by UserInfoTable.phone
    var address: String by UserInfoTable.address
    var user: UserCredentialsEntity by UserCredentialsEntity referencedOn UserInfoTable.user


    fun toUserInfo(): UserInfo {
        return UserInfo(user.email, User.Type.valueOf(user.userType), name, phone, address)
    }
}