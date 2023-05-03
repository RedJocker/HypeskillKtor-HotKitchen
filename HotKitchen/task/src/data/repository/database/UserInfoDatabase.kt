package hotkitchen.data.repository.database

import hotkitchen.domain.model.UserInfo

interface UserInfoDatabase {
    fun userInfoByEmail(email: String): UserInfo?
    fun updateOrCreateUserInfo(name: String, userType: String?, phone: String, email: String, address: String): UserInfo?
    fun deleteUser(email: String)
}