package com.example.productorderservice.product;

import com.example.productorderservice.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class ProductApiTest extends ApiTest {

    @Test
    public void 상품등록() {
        // given
        final AddProductRequest request = ProductSteps.상품등록요청_생성();

        // when
        ExtractableResponse<Response> response = ProductSteps.상품등록요청(request);

        // then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

}
