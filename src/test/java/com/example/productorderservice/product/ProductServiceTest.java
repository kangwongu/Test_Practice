package com.example.productorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

class ProductServiceTest {

    private ProductService productService;
    private ProductPort productPort;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        productPort = new ProductAdapter(productRepository);
        productService = new ProductService(productPort);
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
            Assert.hasText(name, "상품명은 필수입니다.");
            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
        }
    }

    private enum DiscountPolicy {
        NONE
    }

    private class ProductService {
        private final ProductPort productPort;

        private ProductService(ProductPort productPort) {
            this.productPort = productPort;
        }

        public void addProduct(AddProductRequest request) {
            final Product product = new Product(request.name, request.price, request.discountPolicy);

            productPort.save(product);

        }
    }

    private class Product {

        private Long id;
        private final String name;
        private final int price;
        private final DiscountPolicy discountPolicy;

        public Product(final String name, final int price, final DiscountPolicy discountPolicy) {
            Assert.hasText(name, "상품명은 필수입니다.");
            Assert.isTrue(price > 0, "상품 가격은 0보다 커야합니다.");
            Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
        }

        public void assignId(final Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

    private interface ProductPort {
        void save(Product product);
    }

    private class ProductAdapter implements ProductPort {
        private final ProductRepository productRepository;

        private ProductAdapter(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        @Override
        public void save(Product product) {
            productRepository.save(product);
        }
    }

    private class ProductRepository {
        private Long sequence = 0L;
        private Map<Long, Product> persistence = new HashMap<>();


        public void save(final Product product) {
            product.assignId(++sequence);
            persistence.put(product.getId(), product);
        }
    }
}
