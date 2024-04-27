package com.testcafekiosk.spring.api.controller.product

import com.testcafekiosk.spring.api.service.product.ProductService
import com.testcafekiosk.spring.api.service.product.response.ProductResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
) {

    @GetMapping("/api/v1/products/selling")
    fun getSellingProducts(): List<ProductResponse> {
        return productService.getSellingProducts()
    }
}