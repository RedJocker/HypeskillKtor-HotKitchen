package hotkitchen.data.repository.database

import hotkitchen.domain.model.User

interface UserCredentialsDatabase {
    fun userByEmail(email: String): User?
    fun addUser(email: String, type: User.Type, password: String): User?
}