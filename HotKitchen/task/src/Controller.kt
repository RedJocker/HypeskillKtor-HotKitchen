package hotkitchen

import hotkitchen.domain.service.MealService
import hotkitchen.domain.service.OrderService
import hotkitchen.domain.service.UserInfoService
import hotkitchen.domain.service.UserCredentialsService
import hotkitchen.domain.service.tokenService.jwtToken.JwtTokenService
import hotkitchen.presentation.routing.AuthRouting.authRouting
import hotkitchen.presentation.routing.ErrorRouting.errorRouting
import hotkitchen.presentation.routing.MealRouting.mealRouting
import hotkitchen.presentation.routing.OrderRouting.orderRouting
import hotkitchen.presentation.routing.UserInfoRouting.userInfoRouting
import hotkitchen.util.CustomLoggerPlugin
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*



object Controller {
    fun Application.controller(
        userCredentialsService: UserCredentialsService,
        userInfoService: UserInfoService,
        tokenService: JwtTokenService,
        mealService: MealService,
        orderService: OrderService,
        testing: Boolean,
    ) {

        install(ContentNegotiation) { json() }

        install(CustomLoggerPlugin)

        install(StatusPages){ errorRouting() }

        install(Authentication, tokenService.authConfig)

        install(Routing) {
//        trace {
//            //application.log.trace(it.buildText())
//            println(it.buildText())
//        }
            authRouting(userCredentialsService)
            userInfoRouting(userInfoService)
            mealRouting(mealService)
            orderRouting(orderService)
        }
    }
}