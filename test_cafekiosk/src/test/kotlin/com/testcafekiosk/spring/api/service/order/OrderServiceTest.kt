package com.testcafekiosk.spring.api.service.order

import com.testcafekiosk.integration.IntegrationTest
import com.testcafekiosk.spring.api.controller.order.request.OrderCreateRequest
import com.testcafekiosk.spring.api.service.product.ProductService
import com.testcafekiosk.spring.domain.product.Product
import com.testcafekiosk.spring.domain.product.ProductRepository
import com.testcafekiosk.spring.domain.product.ProductSellingStatus
import com.testcafekiosk.spring.domain.product.ProductType
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime

class OrderServiceTest @Autowired constructor(
    private val orderService: OrderService,
    private val productRepository: ProductRepository,
) : IntegrationTest() {


    @Test
    @DisplayName("주문번호 리스트를 받아 주문을 생성할 수 있다")
    fun createOrder() {
        // given
        val registeredDateTime = LocalDateTime.now()

        val product1 = createProduct(ProductType.HANDMADE, "001", 1000)
        val product2 = createProduct(ProductType.HANDMADE, "002", 3000)
        val product3 = createProduct(ProductType.HANDMADE, "003", 5000)
        productRepository.saveAll(listOf(product1, product2, product3))

        val request = OrderCreateRequest(listOf("001", "002"))

        // when
        val result = orderService.createOrder(request, registeredDateTime)

        // then
        assertThat(result.id).isNotNull()
        assertThat(result)
            .extracting("registeredDateTime", "totalPrice")
            .contains(registeredDateTime, 4000)
        assertThat(result.products).hasSize(2)
            .extracting("productNumber", "price")
            .containsExactlyInAnyOrder(
                tuple("001", 1000),
                tuple("002", 3000)
            )

    }

    fun createProduct(type: ProductType, productNumber: String, price: Int): Product {
        return Product(
            type = type,
            productNumber = productNumber,
            price = price,
            sellingStatus = ProductSellingStatus.SELLING,
            name = "메뉴 이름"
        )

    }
}