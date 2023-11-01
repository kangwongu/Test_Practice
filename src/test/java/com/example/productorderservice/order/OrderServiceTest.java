package com.example.productorderservice.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    public void 상품주문() {
        // given
        final Long productId = 1L;
        final int quantity = 2;
        final CreateOrderRequest request = new CreateOrderRequest(productId, quantity);


        // when
        orderService.createOrder(request);

        // then
    }

    private class CreateOrderRequest {

        private Long productId;
        private int quantity;

        public CreateOrderRequest(Long productId, int quantity) {
            Assert.notNull(productId, "상품 ID는 필수입니다.");
            Assert.isTrue(quantity > 0, "수량은 0보다 커야 합니다.");
            this.productId = productId;
            this.quantity = quantity;
        }
    }

    private class OrderService {

        public void createOrder(CreateOrderRequest request) {
            throw new UnsupportedOperationException("UnsupportedOperation");
        }
    }
}
