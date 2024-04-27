package com.testcafekiosk.spring.api.service.order

import com.testcafekiosk.spring.api.controller.order.request.OrderCreateRequest
import com.testcafekiosk.spring.api.service.order.response.OrderResponse
import com.testcafekiosk.spring.domain.order.Order
import com.testcafekiosk.spring.domain.order.OrderRepository
import com.testcafekiosk.spring.domain.product.Product
import com.testcafekiosk.spring.domain.product.ProductRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
) {

    fun createOrder(request: OrderCreateRequest, registeredDateTime: LocalDateTime): OrderResponse {
        val productNumbers = request.productNumbers
        val products = findProductsBy(productNumbers)

        var order = Order.from(products, registeredDateTime)
        order = orderRepository.save(order)

        return OrderResponse.from(order)
    }

    private fun findProductsBy(productNumbers: List<String>): List<Product?> {
        val findProducts = productRepository.findAllByProductNumberIn(productNumbers)
        val productMap = findProducts
            .associateBy { product -> product.productNumber }

        return productNumbers
            .map { productMap[it] }
            .toList()
    }
}