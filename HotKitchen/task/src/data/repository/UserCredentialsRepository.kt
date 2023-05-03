package hotkitchen.data.repository

import hotkitchen.domain.exception.SignupExistingUserException
import hotkitchen.domain.exception.DatabaseException
import hotkitchen.data.repository.database.UserCredentialsDatabase
import hotkitchen.domain.model.User

class UserCredentialsRepository(private val userCredentialsDatabase: UserCredentialsDatabase) {

    fun addUser(email: String, type: User.Type, password: String): User {
        val maybeUser = userCredentialsDatabase.userByEmail(email)

        if(maybeUser != null) {
            throw SignupExistingUserException("There is already an user with ${email} registered")
        }

        val maybeUserAdded = userCredentialsDatabase.addUser(email, type, password)

        return maybeUserAdded ?: throw DatabaseException()
    }

    fun getUser(email: String): User? {
        return userCredentialsDatabase.userByEmail(email)
    }
}