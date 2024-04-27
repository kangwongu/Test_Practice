package com.testcafekiosk.spring.domain.product

import com.testcafekiosk.spring.domain.BaseEntity
import jakarta.persistence.*

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val productNumber: String,

    @Enumerated(EnumType.STRING)
    val type: ProductType,

    @Enumerated(EnumType.STRING)
    val sellingStatus: ProductSellingStatus,

    val name: String,

    val price: Int,

    ) : BaseEntity() {
}