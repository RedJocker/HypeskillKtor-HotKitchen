package hotkitchen.domain.service.tokenService.custom

import hotkitchen.domain.model.User
import hotkitchen.domain.service.tokenService.TokenService

class CustomTokenService: TokenService {

    override fun isValidToken(tokenString: String): Boolean {
        return tokenString.startsWith("abc.")
    }

    override fun produceToken(userType: User.Type, email: String): String {
        return "abc.${userType.typeString}.${email.replace('.', '*')}"
    }

    override fun decodeToken(tokenString: String): Pair<String, String>{
        val (userType, email) = "^abc\\.(.*)\\.(.*)$".toRegex()
            .matchEntire(tokenString)!!.destructured
        return userType to email.replace('*', '.')
    }
}