package hotkitchen.presentation.routing

import hotkitchen.domain.service.UserCredentialsService
import hotkitchen.domain.service.tokenService.TokenService
import hotkitchen.presentation.routing.requestDto.SignInRequest
import hotkitchen.presentation.routing.requestDto.SignUpRequest
import hotkitchen.util.Extensions.getClaims
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

object AuthRouting {

    fun Routing.authRouting(userCredentialsService: UserCredentialsService) {
        post("/signup") {
            val signupRequest = call.receive<SignUpRequest>()
            println("requestBody $signupRequest")

            val response = userCredentialsService.signUpUser(signupRequest)
            call.respond(HttpStatusCode.OK, response)
        }

        post("/signin") {
            val signinRequest = call.receive<SignInRequest>()
            println("requestBody $signinRequest")

            val response = userCredentialsService.signInUser(signinRequest)
            call.respond(HttpStatusCode.OK, response)
        }

        authenticate(TokenService.appScope) {
            get("/validate") {
                println("headers ${call.request.headers}")

                val (email, userType) = call.getClaims("email", "userType")

                call.respond(HttpStatusCode.OK, "Hello, $userType $email")
            }
        }
    }
}