package com.example.productorderservice.product;

import org.springframework.util.Assert;


class AddProductRequest {
    private final String name;
    private final int price;
    private final DiscountPolicy discountPolicy;

    public AddProductRequest(final String name, final int price, final DiscountPolicy discountPolicy) {
        Assert.hasText(name, "상품명은 필수입니다.");
        this.name = name;
        this.price = price;
        this.discountPolicy = discountPolicy;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }
}
