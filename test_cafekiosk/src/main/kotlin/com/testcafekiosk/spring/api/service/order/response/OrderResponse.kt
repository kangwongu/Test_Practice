package com.testcafekiosk.spring.api.service.order.response

import com.testcafekiosk.spring.api.service.product.response.ProductResponse
import com.testcafekiosk.spring.domain.order.Order
import java.time.LocalDateTime

class OrderResponse(
    val id: Long,
    val totalPrice: Int,
    val registeredDateTime: LocalDateTime,
    val products: List<ProductResponse>
) {
    companion object {
        fun from(order: Order): OrderResponse {
            return OrderResponse(
                id = order.id!!,
                totalPrice = order.totalPrice,
                registeredDateTime = order.registeredDateTime,
                products = order.orderProducts
                    .map { ProductResponse.from(it.product) }
                    .toList()
            )
        }
    }
}