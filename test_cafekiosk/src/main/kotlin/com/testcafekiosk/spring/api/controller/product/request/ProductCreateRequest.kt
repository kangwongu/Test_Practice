package com.testcafekiosk.spring.api.controller.product.request

import com.testcafekiosk.spring.domain.product.Product
import com.testcafekiosk.spring.domain.product.ProductSellingStatus
import com.testcafekiosk.spring.domain.product.ProductType

data class ProductCreateRequest(
    val type: ProductType,
    val sellingStatus: ProductSellingStatus,
    val name: String,
    val price: Int,
) {
    fun toEntity(nextProductNumber: String): Product {
        return Product(
            productNumber = nextProductNumber,
            type = type,
            sellingStatus = sellingStatus,
            name = name,
            price = price
        )
    }
}