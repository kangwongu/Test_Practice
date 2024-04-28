package com.testcafekiosk.spring.domain.stock

import com.testcafekiosk.integration.IntegrationTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class StockRepositoryTest @Autowired constructor(
    private val stockRepository: StockRepository,
) : IntegrationTest() {

    @Test
    @DisplayName("상품번호 리스트로 재고를 조회할 수 있다")
    fun findAllByProductNumberIn() {
        // given
        val stock1 = Stock.of("001", 1)
        val stock2 = Stock.of("002", 2)
        val stock3 = Stock.of("003", 3)
        stockRepository.saveAll(listOf(stock1, stock2, stock3))

        // when
        val result = stockRepository.findAllByProductNumberIn(listOf("001", "002"))

        // then
        Assertions.assertThat(result).hasSize(2)
            .extracting("productNumber", "quantity")
            .containsExactlyInAnyOrder(
                Assertions.tuple("001", 1),
                Assertions.tuple("002", 2),
            )

    }
}