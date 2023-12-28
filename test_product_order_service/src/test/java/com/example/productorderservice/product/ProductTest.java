package com.example.productorderservice.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ProductTest {

    @Test
    public void update() {
        // given
        Product product = new Product("상품명", 1000, DiscountPolicy.NONE);

        // when
        product.update("상품 수정", 2000, DiscountPolicy.NONE);

        // then
        assertThat(product.getName()).isEqualTo("상품 수정");
        assertThat(product.getPrice()).isEqualTo(2000);
    }
}
