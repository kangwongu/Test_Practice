package com.testcafekiosk.spring.domain.product

import com.testcafekiosk.integration.IntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

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
        val product1 = createProduct("001", ProductType.HANDMADE, ProductSellingStatus.SELLING, "아메리카노", 4000)
        val product2 = createProduct("002", ProductType.HANDMADE, ProductSellingStatus.HOLD, "카페라떼", 4500)
        val product3 = createProduct("003", ProductType.HANDMADE, ProductSellingStatus.STOP_SELLING, "팥빙수", 7000)
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

    @Test
    @DisplayName("상품번호 리스트로 상품들을 조회할 수 있다")
    fun findAllByProductNumberIn() {
        // given
        val product1 = createProduct("001", ProductType.HANDMADE, ProductSellingStatus.SELLING, "아메리카노", 4000)
        val product2 = createProduct("002", ProductType.HANDMADE, ProductSellingStatus.HOLD, "카페라떼", 4500)
        val product3 = createProduct("003", ProductType.HANDMADE, ProductSellingStatus.STOP_SELLING, "팥빙수", 7000)
        productRepository.saveAll(listOf(product1, product2, product3))

        // when
        val result =
            productRepository.findAllByProductNumberIn(listOf("002", "003"))

        // then
        assertThat(result).hasSize(2)
            .extracting("productNumber", "name", "sellingStatus")
            .containsExactlyInAnyOrder(
                tuple("002", "카페라떼", ProductSellingStatus.HOLD),
                tuple("003", "팥빙수", ProductSellingStatus.STOP_SELLING),
            )

    }

    @Test
    @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어온다")
    fun findLatestProduct() {
        // given
        val targetProductNumber = "003"
        val product1 = createProduct("001", ProductType.HANDMADE, ProductSellingStatus.SELLING, "아메리카노", 4000)
        val product2 = createProduct("002", ProductType.HANDMADE, ProductSellingStatus.HOLD, "카페라떼", 4500)
        val product3 = createProduct(targetProductNumber, ProductType.HANDMADE, ProductSellingStatus.STOP_SELLING, "팥빙수", 7000)

        productRepository.saveAll(listOf(product1, product2, product3))

        // when
        val result = productRepository.findLatestProductNumber()

        // then
        assertThat(result).isEqualTo(targetProductNumber)

    }

    @Test
    @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어올 때, 상품이 하나도 없는 경우에는 null을 반환한다")
    fun findLatestProductWhenProductIsEmpty() {
        // when
        val result = productRepository.findLatestProductNumber()

        // then
        assertThat(result).isNull()

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