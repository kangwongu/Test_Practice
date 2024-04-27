package com.testcafekiosk.spring.domain.product

import com.testcafekiosk.integration.IntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.junit.jupiter.Testcontainers

//@DataJpaTest
////@SpringBootTest
//@ActiveProfiles("test")
//@Testcontainers
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest @Autowired constructor(
    private val productRepository: ProductRepository
) : IntegrationTest() {

//    @Autowired
//    lateinit var productRepository: ProductRepository

    @Test
    @DisplayName("원하는 판매상태를 가지 상품들을 조회한다")
    fun findAllBySellingStatusIn() {
        // given
        val product1 = Product(
            productNumber = "001",
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.SELLING,
            name = "아메리카노",
            price = 4000
        )
        val product2 = Product(
            productNumber = "002",
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.HOLD,
            name = "카페라떼",
            price = 5000
        )
        val product3 = Product(
            productNumber = "003",
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.STOP_SELLING,
            name = "팥빙수",
            price = 7000
        )
        productRepository.saveAll(listOf(product1, product2, product3))

        // when
        val result =
            productRepository.findAllBySellingStatusIn(listOf(ProductSellingStatus.SELLING, ProductSellingStatus.HOLD))

        // then
        assertThat(result).hasSize(2)
            .extracting("productNumber", "name", "sellingStatus")
            .containsExactlyInAnyOrder(
                tuple("001", "아메리카노", ProductSellingStatus.SELLING),
                tuple("002", "카페라떼", ProductSellingStatus.HOLD)
            )

    }

}