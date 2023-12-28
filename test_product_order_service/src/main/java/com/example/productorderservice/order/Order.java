package com.example.productorderservice.order;

import com.example.productorderservice.product.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Product product;
    private int quantity;

    public Order(Product product, int quantity) {

        this.product = product;
        this.quantity = quantity;
    }
}
