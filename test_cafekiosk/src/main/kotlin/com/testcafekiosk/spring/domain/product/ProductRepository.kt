package com.testcafekiosk.spring.domain.product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductRepository : JpaRepository<Product, Long> {

    fun findAllBySellingStatusIn(sellingStatus: List<ProductSellingStatus>): List<Product>

    fun findAllByProductNumberIn(productNumbers: List<String>): List<Product>

    @Query("select p.productNumber from Product p " +
            "order by p.id desc " +
            "limit 1")
    fun findLatestProductNumber(): String?
}