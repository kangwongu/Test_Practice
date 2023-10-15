package com.example.productorderservice.product;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPort productPort;


    @Test
    public void 상품등록() {
        // given
        final AddProductRequest request = 상품등록요청_생성();

        // when
        productService.addProduct(request);
        
        // then
    }

    private static AddProductRequest 상품등록요청_생성() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new AddProductRequest(name, price, discountPolicy);
    }

    @Test
    public void 상품조회() {
        // given
        productService.addProduct(ProductSteps.상품등록요청_생성());
        final long productId = 1L;

        // when
        final GetProductResponse response = productService.getProduct(productId);

        // then
        assertThat(response).isNotNull();
    }

    @Test
    public void 상품수정() {
        // given
        productService.addProduct(ProductSteps.상품등록요청_생성());
        final Long productId = 1L;
        final UpdateProductRequest request = new UpdateProductRequest("상품 수정", 2000, DiscountPolicy.NONE);

        // when
        productService.updateProduct(productId, request);

        // then
        GetProductResponse response = productService.getProduct(productId);
        assertThat(response.getName()).isEqualTo("상품 수정");
        assertThat(response.getPrice()).isEqualTo(2000);
    }

}
