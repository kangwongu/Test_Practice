package com.testcafekiosk.spring.domain.order

import com.testcafekiosk.spring.domain.product.Product
import com.testcafekiosk.spring.domain.product.ProductSellingStatus
import com.testcafekiosk.spring.domain.product.ProductType
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class OrderTest(

) {

    @Test
    @DisplayName("주문 생성 시, 상품 리스트에서 주문의 총 금액을 계산한다")
    fun from() {
        // given
        val registeredDateTime = LocalDateTime.now()
        val products = listOf(
            createProduct("001", 1000),
            createProduct("002", 2000)
        )

        // when
        val result = Order.from(products, registeredDateTime)

        // then
        assertThat(result.totalPrice).isEqualTo(3000)
    }

    @Test
    @DisplayName("주문 생성 시, 주문 상태는 INIT이다")
    fun from2() {
        // given
        val registeredDateTime = LocalDateTime.now()
        val products = listOf(
            createProduct("001", 1000),
            createProduct("002", 2000)
        )

        // when
        val result = Order.from(products, registeredDateTime)

        // then
        assertThat(result.orderStatus).isEqualByComparingTo(OrderStatus.INIT)   // enum 비교
    }

    @Test
    @DisplayName("주문 생성 시, 주문 등록 시간을 기록한다")
    fun from3() {
        // given
        val registeredDateTime = LocalDateTime.now()
        val products = listOf(
            createProduct("001", 1000),
            createProduct("002", 2000)
        )

        // when
        val result = Order.from(products, registeredDateTime)


        // then
        assertThat(result.registeredDateTime).isEqualTo(registeredDateTime)   // enum 비교
    }

    @Test
    @DisplayName("주문 생성 시, 연관관계 편의 메소드를 통해 의존관계를 세팅한다")
    fun from4() {
        // given
        val registeredDateTime = LocalDateTime.now()
        val products = listOf(
            createProduct("001", 1000),
            createProduct("002", 2000)
        )

        // when
        val result = Order.from(products, registeredDateTime)
//        result.registerOrderProduct(products)

        // then
        assertThat(result.orderProducts).hasSize(2)
    }

    private fun createProduct(productNumber: String, price: Int): Product {
        return Product(
            type = ProductType.HANDMADE,
            productNumber = productNumber,
            price = price,
            sellingStatus = ProductSellingStatus.SELLING,
            name = "메뉴 이름"
        )

    }
}
