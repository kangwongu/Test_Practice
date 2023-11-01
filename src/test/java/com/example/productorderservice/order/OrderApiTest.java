package com.example.productorderservice.order;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.product.ProductSteps;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.*;

public class OrderApiTest extends ApiTest {

    @Test
    public void 상품주문() {
        // given
        ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
        final CreateOrderRequest request = OrderSteps.상품주문요청_생성();

        // when
        final ExtractableResponse<Response> response = OrderSteps.상품주문요청(request);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }
}
