package com.testcafekiosk.spring.api.controller.order

import com.testcafekiosk.spring.api.ApiResponse
import com.testcafekiosk.spring.api.controller.order.request.OrderCreateRequest
import com.testcafekiosk.spring.api.service.order.OrderService
import com.testcafekiosk.spring.api.service.order.response.OrderResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class OrderController(
    private val orderService: OrderService,
) {

    @PostMapping("/api/v1/orders/new")
    fun createOrder(@Valid @RequestBody request: OrderCreateRequest): ApiResponse<OrderResponse> {
        val registeredDateTime = LocalDateTime.now()
        return ApiResponse.ok(orderService.createOrder(request.toServiceRequest(), registeredDateTime))
    }
}