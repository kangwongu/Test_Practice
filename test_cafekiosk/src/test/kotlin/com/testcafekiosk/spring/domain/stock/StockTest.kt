package com.testcafekiosk.spring.domain.stock

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StockTest {

    @Test
    @DisplayName("재고의 수량이 제공된 수량보다 적은 지 확인한다")
    fun isQuantityLessThan() {
        // given
        val stock = Stock.of("001", 1)
        val givenQuantity = 2

        // when
        val result = stock.isQuantityLessThan(givenQuantity)

        // then
        assertThat(result).isTrue()
    }

    @Test
    @DisplayName("재고를 주어진 개수만큼 차감할 수 있다")
    fun deductQuantity() {
        // given
        val stock = Stock.of("001", 1)
        val givenQuantity = 1

        // when
        stock.deductQuantity(givenQuantity)

        // then
        assertThat(stock.quantity).isZero()
    }

    @Test
    @DisplayName("재고보다 많은 수의 수량으로 차감 시도하는 경우, 예외가 발생한다")
    fun deductQuantity2() {
        // given
        val stock = Stock.of("001", 1)
        val givenQuantity = 2

        // when
        // then
        assertThatThrownBy { stock.deductQuantity(givenQuantity) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("차감할 재고 수량이 없어요")

    }
}