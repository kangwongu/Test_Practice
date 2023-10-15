package com.example.productorderservice.product;

import com.example.productorderservice.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductApiTest extends ApiTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void 상품등록() {
        // given
        final AddProductRequest request = ProductSteps.상품등록요청_생성();

        // when
        ExtractableResponse<Response> response = ProductSteps.상품등록요청(request);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void 상품조회() {
        // given
        ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
        long productId = 1L;

        // when
        ExtractableResponse<Response> response = ProductSteps.상품조회요청(productId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
    }

    @Test
    public void 상품수정() {
        // given
        ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
        long productId = 1L;

        // when
        ExtractableResponse<Response> response = ProductSteps.상품수정요청(productId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(productRepository.findById(1L).get().getName()).isEqualTo("상품 수정");
    }
}
