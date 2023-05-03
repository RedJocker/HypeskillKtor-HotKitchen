package hotkitchen.data.repository

import hotkitchen.data.repository.database.UserInfoDatabase
import hotkitchen.domain.model.UserInfo

class UserInfoRepository(private val userInfoDatabase: UserInfoDatabase) {



    fun getUserInfoByMail(email : String): UserInfo? {
        return userInfoDatabase.userInfoByEmail(email)
    }

    fun updateOrCreateUserInfo(name: String, phone: String, email: String, address: String): UserInfo? {
        return userInfoDatabase.updateOrCreateUserInfo(name, null, phone, email, address)
    }

    fun updateOrCreateUserInfo(name: String, userType: String, phone: String, email: String, address: String): UserInfo? {
        return userInfoDatabase.updateOrCreateUserInfo(name, userType, phone, email, address)
    }

    fun deleteUser(email: String) {
        userInfoDatabase.deleteUser(email)

    }
}