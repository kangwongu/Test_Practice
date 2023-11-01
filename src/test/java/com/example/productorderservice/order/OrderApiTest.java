package com.example.productorderservice.order;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.product.ProductSteps;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.*;

public class OrderApiTest extends ApiTest {

    @Test
    public void 상품주문() {
        // given
        ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
        final CreateOrderRequest request = OrderSteps.상품주문요청_생성();

        // when
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/orders")
                .then()
                .log().all().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }
}
