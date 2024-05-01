package com.testcafekiosk.spring.api.service.product.request

import com.testcafekiosk.spring.domain.product.Product
import com.testcafekiosk.spring.domain.product.ProductSellingStatus
import com.testcafekiosk.spring.domain.product.ProductType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.NotNull

data class ProductCreateServiceRequest(

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