package com.testcafekiosk.spring.domain.order

import com.testcafekiosk.spring.domain.BaseEntity
import com.testcafekiosk.spring.domain.product.Product
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    val orderStatus: OrderStatus,

    val totalPrice: Int,

    val registeredDateTime: LocalDateTime,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderProducts: MutableList<OrderProduct> = mutableListOf()

) : BaseEntity() {

    fun registerOrderProduct(products: List<Product?>) {
        val orderProducts = products
            .map {
                OrderProduct(
                    order = this,
                    product = it!!
                )
            }
            .toMutableList()
        this.orderProducts = orderProducts
    }

    companion object {
        fun from(
            products: List<Product?>,
            registeredDateTime: LocalDateTime,
            orderStatus: OrderStatus = OrderStatus.INIT
        ): Order {
            val order = Order(
                orderStatus = orderStatus,
                totalPrice = products.sumOf { it!!.price },
                registeredDateTime = registeredDateTime,
//                orderProducts = registerOrderProduct(products)
            )
            order.registerOrderProduct(products)
            return order
        }
    }


}