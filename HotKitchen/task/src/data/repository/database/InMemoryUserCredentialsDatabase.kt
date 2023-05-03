package hotkitchen.data.repository.database

import hotkitchen.domain.model.User

object InMemoryUserCredentialsDatabase: UserCredentialsDatabase {

    private val database: MutableList<User> = mutableListOf()

    override fun userByEmail(email: String): User? {
        return database.firstOrNull { it.email == email }
    }

    override fun addUser(email: String, type: User.Type, password: String): User {
        val user = User(email, type, password)
        database.add(user)
        return user
    }
}