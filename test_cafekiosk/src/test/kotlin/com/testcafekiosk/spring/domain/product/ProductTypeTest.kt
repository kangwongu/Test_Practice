package com.testcafekiosk.spring.domain.product

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProductTypeTest {

    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지를 확인한다")
    fun containsStockType() {
        // given
        val givenType = ProductType.BAKERY

        // when
        val result = ProductType.containsStockType(givenType)

        // then
        Assertions.assertThat(result).isTrue()
    }
}