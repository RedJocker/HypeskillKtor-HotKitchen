package hotkitchen.presentation.routing

import hotkitchen.domain.model.User
import hotkitchen.domain.service.UserInfoService
import hotkitchen.domain.service.tokenService.TokenService
import hotkitchen.presentation.routing.requestDto.UserInfoUpdateRequest
import hotkitchen.util.Extensions.getClaims
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

object UserInfoRouting {

    fun Routing.userInfoRouting(userInfoService: UserInfoService) {
        authenticate(TokenService.appScope) {
            get("/me")  {
                println("headers ${call.request.headers}")
                val (email, ) = call.getClaims("email")
                val userInfo = userInfoService.getUserInfo(email)
                println("email: $email")

                call.respond(HttpStatusCode.OK, userInfo)
            }

            put("/me") {
                println("headers ${call.request.headers}")
                val userInfoUpdateRequest = call.receive<UserInfoUpdateRequest>()
                println("requestBody $userInfoUpdateRequest")
                val (email, userTypeStr) = call.getClaims("email", "userType")
                println("email: $email, userType: $userTypeStr")

                val response = userInfoService.updateUserInfo(email, User.Type.fromTypeString(userTypeStr)!!, userInfoUpdateRequest)

                call.respond(HttpStatusCode.OK, response)
            }

            delete("/me") {
                println("headers ${call.request.headers}")
                val (email, ) = call.getClaims("email")
                println("email: $email")

                userInfoService.deleteUser(email)

                call.respond(HttpStatusCode.OK, HttpStatusCode.OK)
            }
        }
    }
}