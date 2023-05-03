package hotkitchen.presentation.routing

import hotkitchen.domain.exception.DeniedAccessException
import hotkitchen.domain.model.User
import hotkitchen.domain.service.MealService
import hotkitchen.domain.service.tokenService.TokenService
import hotkitchen.presentation.routing.requestDto.AddCategoryRequest
import hotkitchen.presentation.routing.requestDto.AddMealRequest
import hotkitchen.presentation.routing.responseDto.ApiResponse
import hotkitchen.util.Extensions.getClaims
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

object MealRouting {

    fun Routing.mealRouting(mealService: MealService) {
        authenticate(TokenService.appScope) {

            route("/meals") {
                post {
                    println("headers ${call.request.headers}")
                    val request = call.receive<AddMealRequest>()
                    println("requestBody $request")

                    val (userType, ) = call.getClaims("userType")
                    if(userType != User.Type.STAFF.typeString) {
                        throw DeniedAccessException()
                    } else {
                        val response = mealService.addMeal(request)
                        call.respond(HttpStatusCode.OK, response)
                    }
                }

                get {
                    println("headers ${call.request.headers}")
                    println("query parameters ${call.request.queryParameters}")
                    val maybeMealId = call.request.queryParameters["id"]

                    val response = if(maybeMealId == null) {
                        mealService.getMealList()
                    } else {
                        mealService.getMeal(maybeMealId)
                    }

                    call.respond(HttpStatusCode.OK, response)
                }
            }

            route("/categories") {

                post {
                    println("headers ${call.request.headers}")
                    val request = call.receive<AddCategoryRequest>()
                    println("requestBody $request")

                    val (userType, ) = call.getClaims("userType")

                    if (userType != User.Type.STAFF.typeString) {
                        throw DeniedAccessException()
                    } else {
                        val response = mealService.addCategory(request)
                        call.respond(HttpStatusCode.OK, response)
                    }
                }

                get {
                    println("headers ${call.request.headers}")
                    println("query parameters ${call.request.queryParameters}")
                    val maybeCategoryId = call.request.queryParameters["id"]

                    val response: ApiResponse = if(maybeCategoryId == null) {
                        mealService.getListCategory()
                    } else {
                        mealService.getCategory(maybeCategoryId)
                    }

                    call.respond(HttpStatusCode.OK, response)
                }
            }
        }

    }
}