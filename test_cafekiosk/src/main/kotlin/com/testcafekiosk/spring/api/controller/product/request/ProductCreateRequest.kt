package com.testcafekiosk.spring.api.controller.product.request

import com.testcafekiosk.spring.api.service.product.request.ProductCreateServiceRequest
import com.testcafekiosk.spring.domain.product.ProductSellingStatus
import com.testcafekiosk.spring.domain.product.ProductType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class ProductCreateRequest(

    @field:NotNull(message = "상품 타입은 필수입니다.")
    val type: ProductType?,

    @field:NotNull(message = "상품 판매상태는 필수입니다.")
    val sellingStatus: ProductSellingStatus?,

    @field:NotBlank(message = "상품 이름은 필수입니다.")
    val name: String?,

    @field:Positive(message = "상품 가격은 양수이어야 합니다.")
    val price: Int?,
) {

    fun toServiceRequest(): ProductCreateServiceRequest {
        return ProductCreateServiceRequest(
            type = type!!,
            sellingStatus = sellingStatus!!,
            name = name!!,
            price = price!!
        )
    }
}