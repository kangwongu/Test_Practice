package com.example.productorderservice.product;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ProductUpdateTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        final ProductPort productPort = new ProductPort() {
            @Override
            public void save(Product product) {

            }

            @Override
            public Product getProduct(long productId) {
                return null;
            }
        };
        productService = new ProductService(productPort);
    }

    @Test
    public void 상품수정() {
        // given
        final Long productId = 1L;
        final UpdateProductRequest request = new UpdateProductRequest("상품 수정", 2000, DiscountPolicy.NONE);

        // when
        productService.updateProduct(productId, request);

        // then
    }

}
