package com.testcafekiosk.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.testcafekiosk.spring.api.controller.order.OrderController
import com.testcafekiosk.spring.api.controller.product.ProductController
import com.testcafekiosk.spring.api.service.order.OrderService
import com.testcafekiosk.spring.api.service.product.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest(controllers = [ProductController::class, OrderController::class])
abstract class ControllerTest {

    @Autowired
    internal lateinit var mockMvc: MockMvc

    @Autowired
    internal lateinit var objectMapper: ObjectMapper

    // 서비스는 mocking 처리, 임시
    @MockBean
    internal lateinit var productService: ProductService

    @MockBean
    internal lateinit var orderService: OrderService
}