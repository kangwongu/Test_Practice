package com.testcafekiosk.spring.api.service.order

import com.testcafekiosk.spring.api.controller.order.request.OrderCreateRequest
import com.testcafekiosk.spring.api.service.order.response.OrderResponse
import com.testcafekiosk.spring.domain.order.Order
import com.testcafekiosk.spring.domain.order.OrderRepository
import com.testcafekiosk.spring.domain.product.Product
import com.testcafekiosk.spring.domain.product.ProductRepository
import com.testcafekiosk.spring.domain.product.ProductType
import com.testcafekiosk.spring.domain.stock.Stock
import com.testcafekiosk.spring.domain.stock.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.stream.Collector
import java.util.stream.Collectors

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
    private val stockRepository: StockRepository,
) {

    @Transactional
    fun createOrder(request: OrderCreateRequest, registeredDateTime: LocalDateTime): OrderResponse {
        val productNumbers = request.productNumbers
        val products = findProductsBy(productNumbers)

        deductStockQuantities(products)

        var order = Order.from(products, registeredDateTime)
        order = orderRepository.save(order)

        return OrderResponse.from(order)
    }

    private fun deductStockQuantities(products: List<Product?>) {
        // 재고 차감 체크가 필요한 상품들을 filter
        val stockProductNumbers = extractStockProductNumbers(products)

        // 재고 엔티티 조회
        val stockMap = createStockMapBy(stockProductNumbers)

        // 상품별 counting
        val productCountingMap = createCountingMapBy(stockProductNumbers)

        // 재고 차감 시도
        for (stockProductNumber in stockProductNumbers.toSet()) {
            val stock = stockMap[stockProductNumber]
            val quantity = productCountingMap[stockProductNumber]

            if (stock!!.isQuantityLessThan(quantity)) {
                throw IllegalStateException("재고가 부족한 상품이 있어요.")
            }
            stock.deductQuantity(quantity)
        }
    }

    private fun extractStockProductNumbers(products: List<Product?>): List<String> {
        val stockProductNumbers = products
            .filter { ProductType.containsStockType(it!!.type) }
            .map { it!!.productNumber }
            .toList()
        return stockProductNumbers
    }

    private fun createStockMapBy(stockProductNumbers: List<String>): Map<String, Stock> {
        val stocks = stockRepository.findAllByProductNumberIn(stockProductNumbers)
        val stockMap = stocks
            .associateBy { it.productNumber }
        return stockMap
    }

    private fun createCountingMapBy(stockProductNumbers: List<String>) = stockProductNumbers
        .associateBy({ it }, { stockProductNumbers.count { productNumber -> it == productNumber } })

    private fun findProductsBy(productNumbers: List<String>): List<Product?> {
        val findProducts = productRepository.findAllByProductNumberIn(productNumbers)
        val productMap = findProducts
            .associateBy { product -> product.productNumber }

        return productNumbers
            .map { productMap[it] }
            .toList()
    }
}