package com.example.productorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @Test
    public void 상품등록() {
        // given
        final String name = "상품명";
        final int price = 1000;
        final AddProductRequest request = new AddProductRequest(name, price, DiscountPolicy.NONE);

        // when
        productService.addProduct(request);
        
        // then
    }

    private static class AddProductRequest {
        private final String name;
        private final int price;
        private final DiscountPolicy discountPolicy;

        public AddProductRequest(final String name, final int price, final DiscountPolicy discountPolicy) {
            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
            Assert.hasText(name, "상품명은 필수입니다.");

        }
    }

    private enum DiscountPolicy {
        NONE
    }

    private class ProductService {
        public void addProduct(AddProductRequest request) {
            throw new UnsupportedOperationException("Unsupported addProduct");
        }
    }
}
