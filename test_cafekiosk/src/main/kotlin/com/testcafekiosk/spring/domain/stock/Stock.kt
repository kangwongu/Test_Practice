package com.testcafekiosk.spring.domain.stock

import com.testcafekiosk.spring.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Stock(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val productNumber: String,

    var quantity: Int,

) : BaseEntity() {

    fun isQuantityLessThan(quantity: Int?): Boolean {
        return this.quantity < quantity!!
    }

    fun deductQuantity(quantity: Int?) {
        if (isQuantityLessThan(quantity)) {
            throw IllegalStateException("차감할 재고 수량이 없어요")
        }
        this.quantity -= quantity!!
    }

    companion object {
        fun of(productNumber: String, quantity: Int): Stock {
            return Stock(
                productNumber = productNumber,
                quantity = quantity
            )
        }
    }
}