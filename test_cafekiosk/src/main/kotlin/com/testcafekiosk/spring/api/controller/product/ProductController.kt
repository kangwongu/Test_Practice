package com.testcafekiosk.spring.api.controller.product

import com.testcafekiosk.spring.api.ApiResponse
import com.testcafekiosk.spring.api.controller.product.request.ProductCreateRequest
import com.testcafekiosk.spring.api.service.product.ProductService
import com.testcafekiosk.spring.api.service.product.response.ProductResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
) {

    @PostMapping("/api/v1/products/new")
    fun createProduct(@Valid @RequestBody request: ProductCreateRequest): ApiResponse<ProductResponse> {
        return ApiResponse.ok(productService.createProduct(request.toServiceRequest()))
    }

    @GetMapping("/api/v1/products/selling")
    fun getSellingProducts(): ApiResponse<List<ProductResponse>> {
        return ApiResponse.ok(productService.getSellingProducts())
    }
}