package com.testcafekiosk.spring.api.service.product.response

import com.testcafekiosk.spring.domain.product.Product
import com.testcafekiosk.spring.domain.product.ProductSellingStatus
import com.testcafekiosk.spring.domain.product.ProductType

class ProductResponse(
    var id: Long? = null,
    val productNumber: String,
    val type: ProductType,
    val sellingStatus: ProductSellingStatus,
    val name: String,
    val price: Int
) {
    companion object {
        fun from(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id,
                productNumber = product.productNumber,
                type = product.type,
                sellingStatus = product.sellingStatus,
                name = product.name,
                price = product.price
            )
        }
    }
}