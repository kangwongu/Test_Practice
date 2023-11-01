package com.example.productorderservice.order;

import com.example.productorderservice.product.Product;

class Order {
    private Long id;
    private Product product;
    private int quantity;

    public Order(Product product, int quantity) {

        this.product = product;
        this.quantity = quantity;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
