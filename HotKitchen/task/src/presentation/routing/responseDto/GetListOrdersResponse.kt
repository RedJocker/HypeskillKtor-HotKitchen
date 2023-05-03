package hotkitchen.presentation.routing.responseDto

import hotkitchen.domain.model.Order

data class GetListOrdersResponse(private val orders: List<Order>) : List<Order> by orders, ApiResponse