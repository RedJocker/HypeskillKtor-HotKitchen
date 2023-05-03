package hotkitchen.util

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

object Extensions {

    fun ApplicationCall.getClaims(vararg name: String): List<String> {
        val jwtPrincipal = this.principal<JWTPrincipal>()!!

        return name.map {
            jwtPrincipal[it]!!
        }
    }
}