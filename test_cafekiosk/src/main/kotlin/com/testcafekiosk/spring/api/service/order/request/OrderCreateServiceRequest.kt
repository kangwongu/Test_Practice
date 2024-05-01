package com.testcafekiosk.spring.api.service.order.request


data class OrderCreateServiceRequest(
    val productNumbers: List<String>?
)