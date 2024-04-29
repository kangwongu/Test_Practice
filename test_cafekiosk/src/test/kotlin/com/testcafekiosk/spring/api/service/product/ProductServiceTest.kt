package com.testcafekiosk.spring.api.service.product

import com.testcafekiosk.integration.IntegrationTest
import com.testcafekiosk.spring.api.controller.product.request.ProductCreateRequest
import com.testcafekiosk.spring.domain.product.Product
import com.testcafekiosk.spring.domain.product.ProductRepository
import com.testcafekiosk.spring.domain.product.ProductSellingStatus
import com.testcafekiosk.spring.domain.product.ProductType
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


class ProductServiceTest @Autowired constructor(
    private val productService: ProductService,
    private val productRepository: ProductRepository,
) : IntegrationTest() {

//    @AfterEach
//    fun tearDown() {
//        productRepository.deleteAllInBatch()
//    }

    @Test
    @DisplayName("신규 상품을 등록하면, 상품번호는 가장 최근 상품의 상품번호에서 +1한 값이 세팅된다")
    fun createProduct() {
        // given
        val product1 = createProduct("001", ProductType.HANDMADE, ProductSellingStatus.SELLING, "아메리카노", 4000)
        productRepository.saveAll(listOf(product1))

        val request = ProductCreateRequest(
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.SELLING,
            name = "바닐라 라떼",
            price = 6500
        )

        // when
        val result = productService.createProduct(request)

        // then
        assertThat(result)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .contains("002", ProductType.HANDMADE, ProductSellingStatus.SELLING, "바닐라 라떼", 6500)

        val products = productRepository.findAll()
        assertThat(products).hasSize(2)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .containsExactlyInAnyOrder(
                tuple("001", ProductType.HANDMADE, ProductSellingStatus.SELLING, "아메리카노", 4000),
                tuple("002", ProductType.HANDMADE, ProductSellingStatus.SELLING, "바닐라 라떼", 6500),

            )
    }

    @Test
    @DisplayName("상품이 하나도 없는 경우, 신규 상품을 등록하면 싱픔번호는 001이다")
    fun createProductWhenProductIsEmpty() {
        // given
        val request = ProductCreateRequest(
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.SELLING,
            name = "바닐라 라떼",
            price = 6500
        )

        // when
        val result = productService.createProduct(request)

        // then
        assertThat(result)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .contains("001", ProductType.HANDMADE, ProductSellingStatus.SELLING, "바닐라 라떼", 6500)

        val products = productRepository.findAll()
        assertThat(products).hasSize(1)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .contains(
                tuple("001", ProductType.HANDMADE, ProductSellingStatus.SELLING, "바닐라 라떼", 6500),
            )

    }

    private fun createProduct(productNumber: String, type: ProductType, sellingStatus: ProductSellingStatus, name: String, price: Int): Product {
        return Product(
            productNumber = productNumber,
            type = type,
            sellingStatus = sellingStatus,
            name = name,
            price = price
        )
    }

}