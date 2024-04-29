package com.testcafekiosk.spring.api.controller.product

import com.testcafekiosk.spring.api.controller.product.request.ProductCreateRequest
import com.testcafekiosk.spring.api.service.product.ProductService
import com.testcafekiosk.spring.api.service.product.response.ProductResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
) {

    @PostMapping("/api/v1/products/new")
    fun createProduct(@RequestBody request: ProductCreateRequest) {
        productService.createProduct(request)
    }

    @GetMapping("/api/v1/products/selling")
    fun getSellingProducts(): List<ProductResponse> {
        return productService.getSellingProducts()
    }
}