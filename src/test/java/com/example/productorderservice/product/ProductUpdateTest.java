package com.example.productorderservice.product;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductUpdateTest {

    private ProductService productService;
//    private StupProductPort productPort = new StupProductPort();
    private ProductPort productPort;


    @BeforeEach
    void setUp() {
        productPort = Mockito.mock(ProductPort.class);

        productService = new ProductService(productPort);
    }

    @Test
    public void 상품수정() {
        // given
        final Long productId = 1L;
        final UpdateProductRequest request = new UpdateProductRequest("상품 수정", 2000, DiscountPolicy.NONE);
        final Product product = new Product("상품명", 1000, DiscountPolicy.NONE);
//        productPort.getProduct_will_return  = product;
        Mockito.when(productPort.getProduct(productId)).thenReturn(product);

        // when
        productService.updateProduct(productId, request);

        // then
        assertThat(product.getName()).isEqualTo("상품 수정");
        assertThat(product.getPrice()).isEqualTo(2000);
    }

//    private class StupProductPort implements ProductPort {
//
//        public Product getProduct_will_return;
//
//        @Override
//        public void save(Product product) {
//
//        }
//
//        @Override
//        public Product getProduct(long productId) {
//            return getProduct_will_return;
//        }
//    }
}
