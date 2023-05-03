package hotkitchen.domain.service.tokenService

import hotkitchen.domain.model.User

interface TokenService {

    fun isValidToken(tokenString: String): Boolean

    fun produceToken(userType: User.Type, email: String): String

    fun decodeToken(tokenString: String): Pair<String, String>

    companion object {
        const val appScope = "appScope"
    }
}