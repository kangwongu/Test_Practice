package com.example.productorderservice.order;

import org.springframework.util.Assert;

class CreateOrderRequest {

    private Long productId;
    private int quantity;

    public CreateOrderRequest(Long productId, int quantity) {
        Assert.notNull(productId, "상품 ID는 필수입니다.");
        Assert.isTrue(quantity > 0, "수량은 0보다 커야 합니다.");
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
