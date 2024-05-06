package com.testcafekiosk.spring.api.service

import com.testcafekiosk.integration.IntegrationTest
import com.testcafekiosk.spring.api.service.product.request.ProductCreateServiceRequest
import com.testcafekiosk.spring.client.MailSendClient
import com.testcafekiosk.spring.domain.history.mail.MailSendHistoryRepository
import com.testcafekiosk.spring.domain.order.Order
import com.testcafekiosk.spring.domain.order.OrderRepository
import com.testcafekiosk.spring.domain.order.OrderStatus
import com.testcafekiosk.spring.domain.product.Product
import com.testcafekiosk.spring.domain.product.ProductRepository
import com.testcafekiosk.spring.domain.product.ProductSellingStatus
import com.testcafekiosk.spring.domain.product.ProductType
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import java.time.LocalDate
import java.time.LocalDateTime

class OrderStatisticsServiceTest @Autowired constructor(
    private val orderStatisticsService: OrderStatisticsService,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository,
    private val mailSendHistoryRepository: MailSendHistoryRepository,
) : IntegrationTest() {

    @MockBean
    private lateinit var mailSendClient: MailSendClient

    @Test
    @DisplayName("결제완료된 주문들을 조회하여 매출통계 메일을 전송한다")
    fun sendOrderStatisticsMail() {
        // given
        val now = LocalDateTime.of(2024, 5, 3, 22, 0)

        val product1 = createProduct(ProductType.BOTTLE, "001", 1000)
        val product2 = createProduct(ProductType.BAKERY, "002", 3000)
        val product3 = createProduct(ProductType.HANDMADE, "003", 5000)
        productRepository.saveAll(listOf(product1, product2, product3))

        val order1 = createPaymentCompletedOrder(listOf(product1, product2, product3), LocalDateTime.of(2024, 5, 2, 23, 59, 59))
        val order2 = createPaymentCompletedOrder(listOf(product1, product2, product3), now)
        val order3 = createPaymentCompletedOrder(listOf(product1, product2, product3), LocalDateTime.of(2024, 5, 3, 23, 59, 59))
        val order4 = createPaymentCompletedOrder(listOf(product1, product2, product3), LocalDateTime.of(2024, 5, 4, 0, 0))

        // MockBean으로 모킹한 메소드의 행동을 정의, stub
        `when`(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(true)

        // when
        val result =
            orderStatisticsService.sendOrderStatisticsMail(LocalDate.of(2024, 5, 3), "test@test.com")

        // then
        assertThat(result).isTrue()

        val histories = mailSendHistoryRepository.findAll()
        assertThat(histories).hasSize(1)
            .extracting("content")
            .contains("총 매출 합계는 18000입니다")
    }

    private fun createPaymentCompletedOrder(
        products: List<Product>,
        now: LocalDateTime
    ): Order {
        val order = Order.from(products, now, OrderStatus.PAYMENT_COMPLETED)
        return orderRepository.save(order)
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