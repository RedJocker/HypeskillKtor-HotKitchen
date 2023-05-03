package hotkitchen

import data.repository.database.exposed.ExposedH2OrderDatabase
import hotkitchen.Controller.controller
import hotkitchen.data.repository.MealRepository
import hotkitchen.data.repository.OrderRepository
import hotkitchen.data.repository.UserInfoRepository
import hotkitchen.data.repository.UserCredentialsRepository
import hotkitchen.data.repository.database.MealDatabase
import hotkitchen.data.repository.database.OrderDatabase
import hotkitchen.data.repository.database.UserCredentialsDatabase
import hotkitchen.data.repository.database.UserInfoDatabase
import hotkitchen.data.repository.database.exposed.*
import hotkitchen.data.repository.database.exposed.table.*
import hotkitchen.domain.service.MealService
import hotkitchen.domain.service.OrderService
import hotkitchen.domain.service.UserInfoService
import hotkitchen.domain.service.UserCredentialsService
import hotkitchen.domain.service.tokenService.jwtToken.JwtTokenService
import io.ktor.application.*


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module(testing: Boolean = false) {
    val exposedDatabaseConnection = ExposedDatabaseConnection(
        UserCredentialsTable,
        UserInfoTable,
        MealTable,
        CategoryTable,
        MealToCategoryRelationTable,
        OrderTable
    )
    val userCredentialsDatabase: UserCredentialsDatabase = ExposedH2UserCredentialsDatabase(exposedDatabaseConnection.database)
    val userInfoDatabase: UserInfoDatabase = ExposedH2UserInfoDatabase(exposedDatabaseConnection.database)
    val userInfoRepository: UserInfoRepository = UserInfoRepository(userInfoDatabase)
    val userCredentialsRepository: UserCredentialsRepository = UserCredentialsRepository(userCredentialsDatabase)
    val tokenService: JwtTokenService = JwtTokenService(environment)
    val userCredentialsService = UserCredentialsService(userCredentialsRepository, tokenService)
    val userInfoService = UserInfoService(userInfoRepository)

    val mealDatabase: MealDatabase = ExposedH2MealDatabase(exposedDatabaseConnection.database)
    val mealRepository = MealRepository(mealDatabase)
    val mealService = MealService(mealRepository)

    val orderDatabase: OrderDatabase = ExposedH2OrderDatabase(exposedDatabaseConnection.database)
    val orderRepository = OrderRepository(mealDatabase, orderDatabase)
    val orderService = OrderService(orderRepository)


    controller(
        userCredentialsService = userCredentialsService,
        userInfoService = userInfoService,
        tokenService = tokenService,
        mealService = mealService,
        orderService = orderService,
        testing = testing
    )
}