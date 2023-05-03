package hotkitchen.domain.service

import hotkitchen.data.repository.OrderRepository
import hotkitchen.domain.exception.DatabaseException
import hotkitchen.presentation.routing.requestDto.CreateOrderRequest
import hotkitchen.presentation.routing.responseDto.ApiResponse
import hotkitchen.presentation.routing.responseDto.CreateOrderResponse
import hotkitchen.presentation.routing.responseDto.GetListOrdersResponse

class OrderService(private val orderRepository: OrderRepository) {
    fun createOrder(createOrderRequest: CreateOrderRequest, email: String): ApiResponse {
        val order = orderRepository.createOrderRequest(createOrderRequest.toList(), email)
            ?: throw DatabaseException()

        return CreateOrderResponse.fromOrder(order)
    }

    fun lastOrder(): ApiResponse {
        val order = orderRepository.lastOrder() ?: throw DatabaseException()

        return GetListOrdersResponse(listOf(order.copy(status = "")))
    }
}