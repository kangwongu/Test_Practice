package com.testcafekiosk.spring.api.service.order

import com.testcafekiosk.integration.IntegrationTest
import com.testcafekiosk.spring.api.controller.order.request.OrderCreateRequest
import com.testcafekiosk.spring.api.service.order.request.OrderCreateServiceRequest
import com.testcafekiosk.spring.domain.product.Product
import com.testcafekiosk.spring.domain.product.ProductRepository
import com.testcafekiosk.spring.domain.product.ProductSellingStatus
import com.testcafekiosk.spring.domain.product.ProductType
import com.testcafekiosk.spring.domain.stock.Stock
import com.testcafekiosk.spring.domain.stock.StockRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime

class OrderServiceTest @Autowired constructor(
    private val orderService: OrderService,
    private val productRepository: ProductRepository,
    private val stockRepository: StockRepository,
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

        val request = OrderCreateServiceRequest(listOf("001", "002"))

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

    @Test
    @DisplayName("중복되는 상품번호로 주문을 생성할 수 있다")
    fun createOrderWithDuplicateProductNumbers() {
        // given
        val registeredDateTime = LocalDateTime.now()

        val product1 = createProduct(ProductType.HANDMADE, "001", 1000)
        val product2 = createProduct(ProductType.HANDMADE, "002", 3000)
        val product3 = createProduct(ProductType.HANDMADE, "003", 5000)
        productRepository.saveAll(listOf(product1, product2, product3))

        val request = OrderCreateServiceRequest(listOf("001", "001"))

        // when
        val result = orderService.createOrder(request, registeredDateTime)

        // then
        assertThat(result.id).isNotNull()
        assertThat(result)
            .extracting("registeredDateTime", "totalPrice")
            .contains(registeredDateTime, 2000)
        assertThat(result.products).hasSize(2)
            .extracting("productNumber", "price")
            .containsExactlyInAnyOrder(
                tuple("001", 1000),
                tuple("001", 1000)
            )

    }

    @Test
    @DisplayName("재고와 관련된 상품이 포함되어 있는 주문번호 리스트를 받아 주문을 생성한다")
    fun createOrderWithStock() {
        // given
        val registeredDateTime = LocalDateTime.now()

        val product1 = createProduct(ProductType.BOTTLE, "001", 1000)
        val product2 = createProduct(ProductType.BAKERY, "002", 3000)
        val product3 = createProduct(ProductType.HANDMADE, "003", 5000)
        productRepository.saveAll(listOf(product1, product2, product3))

        val stock1 = Stock.of("001", 2)
        val stock2 = Stock.of("002", 2)
        stockRepository.saveAll(listOf(stock1, stock2))

        val request = OrderCreateServiceRequest(listOf("001", "001", "002", "003"))

        // when
        val result = orderService.createOrder(request, registeredDateTime)

        // then
        assertThat(result.id).isNotNull()
        assertThat(result)
            .extracting("registeredDateTime", "totalPrice")
            .contains(registeredDateTime, 10000)
        assertThat(result.products).hasSize(4)
            .extracting("productNumber", "price")
            .containsExactlyInAnyOrder(
                tuple("001", 1000),
                tuple("001", 1000),
                tuple("002", 3000),
                tuple("003", 5000)
            )
        val stocks = stockRepository.findAll()
        assertThat(stocks).hasSize(2)
            .extracting("productNumber", "quantity")
            .containsExactlyInAnyOrder(
                tuple("001", 0),
                tuple("002", 1)
            )
    }

    @Test
    @DisplayName("재고가 부족한 상품으로 주문을 생성하려는 경우 예외가 발생한다")
    fun createOrderWithNoStock() {
        // given
        val registeredDateTime = LocalDateTime.now()

        val product1 = createProduct(ProductType.BOTTLE, "001", 1000)
        val product2 = createProduct(ProductType.BAKERY, "002", 3000)
        val product3 = createProduct(ProductType.HANDMADE, "003", 5000)
        productRepository.saveAll(listOf(product1, product2, product3))

        val stock1 = Stock.of("001", 0)
        val stock2 = Stock.of("002", 2)
        stockRepository.saveAll(listOf(stock1, stock2))

        val request = OrderCreateServiceRequest(listOf("001", "001", "002", "003"))

        // when
        // then
        assertThatThrownBy { orderService.createOrder(request, registeredDateTime) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("재고가 부족한 상품이 있어요.")
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