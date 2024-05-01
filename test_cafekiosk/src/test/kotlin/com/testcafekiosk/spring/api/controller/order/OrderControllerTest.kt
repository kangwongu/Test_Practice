package com.testcafekiosk.spring.api.controller.order

import com.fasterxml.jackson.databind.ObjectMapper
import com.testcafekiosk.spring.api.controller.order.request.OrderCreateRequest
import com.testcafekiosk.spring.api.controller.product.request.ProductCreateRequest
import com.testcafekiosk.spring.api.service.order.OrderService
import com.testcafekiosk.spring.api.service.product.ProductService
import com.testcafekiosk.spring.domain.product.ProductSellingStatus
import com.testcafekiosk.spring.domain.product.ProductType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [OrderController::class])
class OrderControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
) {

    @MockBean
    private lateinit var orderService: OrderService

    @Test
    @DisplayName("신규 주문을 등록한다")
    fun createOrder() {
        // given
        val request = OrderCreateRequest(productNumbers = listOf("001"))

        // when
        // then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/orders/new")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OK"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty)

    }

    @Test
    @DisplayName("신규 주문을 등록할 때, 상품번호는 1개 이상이어야 한다")
    fun createOrderWithEmptyProductNumbers() {
        // given
        val request = OrderCreateRequest(productNumbers = listOf())

        // when
        // then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/orders/new")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("상품 번호 리스트는 필수입니다."))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty)

    }
}