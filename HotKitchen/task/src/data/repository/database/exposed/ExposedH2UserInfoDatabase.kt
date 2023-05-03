package hotkitchen.data.repository.database.exposed

import hotkitchen.data.repository.database.UserInfoDatabase
import hotkitchen.data.repository.database.exposed.entity.UserCredentialsEntity
import hotkitchen.data.repository.database.exposed.entity.UserInfoEntity
import hotkitchen.data.repository.database.exposed.table.UserCredentialsTable
import hotkitchen.data.repository.database.exposed.table.UserInfoTable
import hotkitchen.domain.exception.InvalidDeleteUserRequestException
import hotkitchen.domain.model.UserInfo
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class ExposedH2UserInfoDatabase(private val database: Database): UserInfoDatabase {
    override fun userInfoByEmail(email: String): UserInfo? {
        return transaction(database) {

            val query = UserInfoTable.innerJoin(UserCredentialsTable).select {
                UserCredentialsTable.email eq email
            }.limit(1)
            val userInfo = UserInfoEntity.wrapRows(query).firstOrNull()?.toUserInfo()

            userInfo
        }
    }

    override fun updateOrCreateUserInfo(
        name: String,
        userType: String?,
        phone: String,
        email: String,
        address: String
    ): UserInfo? {
        return transaction(database) {

            if(userType != null) {
                UserCredentialsTable.update({ UserCredentialsTable.email eq email }) {
                    it[UserCredentialsTable.userType] = userType
                }
            }

            val query = UserInfoTable.innerJoin(UserCredentialsTable).select {
                UserCredentialsTable.email eq email
            }
            val maybeUserInfoEntity = UserInfoEntity.wrapRows(query).firstOrNull()

            val userInfoId = if(maybeUserInfoEntity != null) {
                UserInfoTable.update({ UserInfoTable.id eq maybeUserInfoEntity.id}) {
                    it[this.name] = name
                    it[this.phone] = phone
                    it[this.address] = address
                }
                maybeUserInfoEntity.id
            } else {
                val userCredentialsEntity = UserCredentialsEntity.find {
                    UserCredentialsTable.email eq email
                }.limit(1).firstOrNull()!!

                UserInfoTable.insertAndGetId {
                    it[this.address] = address
                    it[this.name] = name
                    it[this.user] = userCredentialsEntity.id
                    it[this.phone] = phone
                }
            }

            UserInfoEntity.find {
                UserInfoTable.id eq userInfoId
            }.limit(1).firstOrNull()?.toUserInfo()
        }
    }

    override fun deleteUser(email: String) {
        return transaction(database) {



            val query = UserInfoTable.innerJoin(UserCredentialsTable).select {
                UserCredentialsTable.email eq email
            }.limit(1)
            val maybeUserInfoEntity = UserInfoEntity.wrapRows(query).firstOrNull() ?: throw InvalidDeleteUserRequestException()

            maybeUserInfoEntity.delete()
            maybeUserInfoEntity.user.delete()
        }
    }
}