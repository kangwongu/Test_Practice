package com.testcafekiosk.spring.api.controller.order

import com.testcafekiosk.spring.api.controller.order.request.OrderCreateRequest
import com.testcafekiosk.spring.api.service.order.OrderService
import com.testcafekiosk.spring.api.service.product.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class OrderController(
    private val orderService: OrderService,
) {

    @PostMapping("/api/v1/orders/new")
    fun createOrder(@RequestBody request: OrderCreateRequest) {
        val registeredDateTime = LocalDateTime.now()
        orderService.createOrder(request, registeredDateTime)
    }
}