package hotkitchen.presentation.routing

import hotkitchen.domain.exception.DeniedAccessException
import hotkitchen.domain.model.Order
import hotkitchen.domain.model.User
import hotkitchen.domain.service.OrderService
import hotkitchen.domain.service.tokenService.TokenService
import hotkitchen.presentation.routing.requestDto.CreateOrderRequest
import hotkitchen.presentation.routing.responseDto.ApiResponse
import hotkitchen.presentation.routing.responseDto.GetListOrdersResponse
import hotkitchen.util.Extensions.getClaims
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

object OrderRouting {

    fun Routing.orderRouting(orderService: OrderService) {
        authenticate(TokenService.appScope) {
            post("/order") {
                println("headers ${call.request.headers}")
                val request = call.receive<CreateOrderRequest>()
                println("requestBody $request")

                val (email, ) = call.getClaims("email")
                println("email: $email")

                val response = orderService.createOrder(request, email)

                call.respond(HttpStatusCode.OK, response)
            }

            post("/order/{orderId}/markReady") {
                println("headers: ${call.request.headers}")
                val request = call.receiveText()
                println("requestBody: $request")
                println("parameters: ${call.parameters}")
                val (userType, ) = call.getClaims("userType")
                println("userType: $userType")

                if (userType != User.Type.STAFF.typeString) {
                    throw DeniedAccessException()
                } else {
                    val response = HttpStatusCode.OK
                    call.respond(HttpStatusCode.OK, response)
                }
            }
        }

        get("/orderHistory") {
            println("headers: ${call.request.headers}")
            val request = call.receiveText()
            println("requestBody: $request")

            val response: ApiResponse = orderService.lastOrder()

            call.respond(HttpStatusCode.OK, response)
        }

        get("/orderIncomplete") {
            println("headers: ${call.request.headers}")
            val request = call.receiveText()
            println("requestBody: $request")

            val response: ApiResponse = GetListOrdersResponse(listOf())

            call.respond(HttpStatusCode.OK, response)
        }

    }
}