package com.testcafekiosk.spring.api.service.product

import com.testcafekiosk.spring.api.controller.product.request.ProductCreateRequest
import com.testcafekiosk.spring.api.service.product.response.ProductResponse
import com.testcafekiosk.spring.domain.product.ProductRepository
import com.testcafekiosk.spring.domain.product.ProductSellingStatus
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {

    fun getSellingProducts(): List<ProductResponse> {
        val products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay())

        return products
            .map { ProductResponse.from(it) }
            .toList()
    }

    fun createProduct(request: ProductCreateRequest): ProductResponse {
        val nextProductNumber = createNextProductNumber()

        var product = request.toEntity(nextProductNumber)
        product = productRepository.save(product)

        return ProductResponse.from(product)
    }

    private fun createNextProductNumber(): String {
        val latestProductNumber = productRepository.findLatestProductNumber()
            ?: return "001"

        val latestProductNumberInt = latestProductNumber.toInt()
        val nextProductNumberInt = latestProductNumberInt + 1

        return String.format("%03d", nextProductNumberInt)
    }
}