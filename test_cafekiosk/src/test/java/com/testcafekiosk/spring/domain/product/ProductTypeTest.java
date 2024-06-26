//package com.testcafekiosk.spring.domain.product;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class ProductTypeTest {
//
//    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다")
//    @Test
//    public void containsStockType() {
//        // given
//        ProductType productType = ProductType.HANDMADE;
//
//        // when
//        boolean result = ProductType.containsStockType(productType);
//
//        // then
//        assertThat(result).isFalse();
//    }
//
//    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다")
//    @Test
//    public void containsStockType2() {
//        // given
//        ProductType productType = ProductType.BAKERY;
//
//        // when
//        boolean result = ProductType.containsStockType(productType);
//
//        // then
//        assertThat(result).isTrue();
//    }
//
//}