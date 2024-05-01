package com.testcafekiosk.spring.api.controller.product

import com.fasterxml.jackson.databind.ObjectMapper
import com.testcafekiosk.spring.api.controller.product.request.ProductCreateRequest
import com.testcafekiosk.spring.api.service.product.ProductService
import com.testcafekiosk.spring.api.service.product.response.ProductResponse
import com.testcafekiosk.spring.domain.product.ProductSellingStatus
import com.testcafekiosk.spring.domain.product.ProductType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [ProductController::class])
class ProductControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
) {

    // 서비스는 mocking 처리, 임시
    @MockBean
    private lateinit var productService: ProductService

    @Test
    @DisplayName("신규 상품을 등록한다")
    fun createProduct() {
        // given
        val request = ProductCreateRequest(
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.SELLING,
            name = "바닐라 라떼",
            price = 6500
        )

        // when
        // then
        mockMvc.perform(
            post("/api/v1/products/new")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isOk)

    }

    @Test
    @DisplayName("신규 상품을 등록할 때, 상품 타입은 필수값이다")
    fun createProductWithoutProductType() {
        // given
        val request = ProductCreateRequest(
            type = null,
            sellingStatus = ProductSellingStatus.SELLING,
            name = "바닐라 라떼",
            price = 6500
        )

        // when
        // then
        mockMvc.perform(
            post("/api/v1/products/new")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("상품 타입은 필수입니다."))
            .andExpect(jsonPath("$.data").isEmpty)

    }

    @Test
    @DisplayName("신규 상품을 등록할 때, 상품 판매상태는 필수값이다")
    fun createProductWithoutProductSellingStatus() {
        // given
        val request = ProductCreateRequest(
            type = ProductType.HANDMADE,
            sellingStatus = null,
            name = "바닐라 라떼",
            price = 6500
        )

        // when
        // then
        mockMvc.perform(
            post("/api/v1/products/new")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("상품 판매상태는 필수입니다."))
            .andExpect(jsonPath("$.data").isEmpty)

    }

    @Test
    @DisplayName("신규 상품을 등록할 때, 상품 이름은 필수값이다")
    fun createProductWithoutName() {
        // given
        val request = ProductCreateRequest(
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.SELLING,
            name = "",
            price = 6500
        )

        // when
        // then
        mockMvc.perform(
            post("/api/v1/products/new")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("상품 이름은 필수입니다."))
            .andExpect(jsonPath("$.data").isEmpty)

    }

    @Test
    @DisplayName("신규 상품을 등록할 때, 상품 가격은 양수이다")
    fun createProductWithoutZeroPrice() {
        // given
        val request = ProductCreateRequest(
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.SELLING,
            name = "바닐라 라떼",
            price = 0
        )

        // when
        // then
        mockMvc.perform(
            post("/api/v1/products/new")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("상품 가격은 양수이어야 합니다."))
            .andExpect(jsonPath("$.data").isEmpty)

    }

    @Test
    @DisplayName("판매 상품을 조회한다")
    fun getSellingProducts() {
        // given
        val result: List<ProductResponse> = listOf()
        // productService는 Mocking되어 있기 때문에,
        // Mockito를 활용해 productService의 getSellingProducts를 호출 시 stub 해준 반환값을 내려준다
        Mockito.`when`(productService.getSellingProducts()).thenReturn(result)

        // when
        // then
        mockMvc.perform(
            get("/api/v1/products/selling")
//                .queryParam("key", "valeu")
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.status").value("OK"))
            .andExpect(jsonPath("$.message").value("OK"))
            .andExpect(jsonPath("$.data").isArray)

    }

}