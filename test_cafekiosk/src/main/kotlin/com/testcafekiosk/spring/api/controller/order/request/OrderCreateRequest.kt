package com.testcafekiosk.spring.api.controller.order.request

import com.testcafekiosk.spring.api.service.order.request.OrderCreateServiceRequest
import jakarta.validation.constraints.NotEmpty

class OrderCreateRequest(

    @field:NotEmpty(message = "상품 번호 리스트는 필수입니다.")
    val productNumbers: List<String>?
) {

    fun toServiceRequest(): OrderCreateServiceRequest {
        return OrderCreateServiceRequest(
            productNumbers = productNumbers
        )
    }
}