package com.testcafekiosk.spring.domain.order

import com.testcafekiosk.spring.domain.BaseEntity
import com.testcafekiosk.spring.domain.product.Product
import jakarta.persistence.*

@Entity
class OrderProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val order: Order,

    @ManyToOne(fetch = FetchType.LAZY)
    val product: Product,

    ) : BaseEntity()
