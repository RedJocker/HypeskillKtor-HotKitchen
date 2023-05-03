package hotkitchen.domain.service.tokenService.jwtToken

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import hotkitchen.domain.exception.UnauthorizedAccessException
import hotkitchen.domain.model.User
import hotkitchen.domain.service.tokenService.TokenService
import hotkitchen.domain.service.tokenService.TokenService.Companion.appScope
import hotkitchen.util.Validation.isNotValidEmail
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import java.util.*

class JwtTokenService(environment: ApplicationEnvironment): TokenService {

    private val secret = environment.config.property("jwt.secret").getString()
    private val issuer = environment.config.property("jwt.issuer").getString()
    private val audience = environment.config.property("jwt.audience").getString()
    private val expirationTime = 1000 * 60 * 10
    private val algorithm = Algorithm.HMAC256(secret)

    private val realm = environment.config.property("jwt.realm").getString()
    private val jwtVerifier = JWT.require(algorithm)
        .withAudience(audience)
        .withIssuer(issuer)
        .withClaimPresence("email")
        .withClaimPresence("userType")
        .build()


    val authConfig: Authentication.Configuration.() -> Unit = {
        jwt(appScope) {
            realm = this@JwtTokenService.realm
            verifier(jwtVerifier)

            validate { credential ->
                val payload = credential.payload
                val email = payload.getClaim("email").asString()
                if(email.isNotValidEmail()) {
                    null
                } else {
                    JWTPrincipal(payload)
                }
            }

            challenge { _, _ ->
                throw UnauthorizedAccessException()
            }
        }
    }

    override fun isValidToken(tokenString: String): Boolean {

        return try {
            jwtVerifier.verify(tokenString)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun produceToken(userType: User.Type, email: String): String {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("email", email)
            .withClaim("userType", userType.typeString)
            .withExpiresAt(Date(System.currentTimeMillis() + expirationTime))
            .sign(algorithm)
    }

    override fun decodeToken(tokenString: String): Pair<String, String> {
        val jwt = JWT.decode(tokenString)
        val email = jwt.getClaim("email").asString()
        val userType = jwt.getClaim("userType").asString()

        return userType to email
    }
}