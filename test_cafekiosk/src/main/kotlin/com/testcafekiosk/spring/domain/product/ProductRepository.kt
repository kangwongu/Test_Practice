package com.testcafekiosk.spring.domain.product

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {

    fun findAllBySellingStatusIn(sellingStatus: List<ProductSellingStatus>): List<Product>

    fun findAllByProductNumberIn(productNumbers: List<String>): List<Product>
}