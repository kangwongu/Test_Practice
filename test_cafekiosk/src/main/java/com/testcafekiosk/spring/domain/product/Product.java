//package com.testcafekiosk.spring.domain.product;
//
//import com.testcafekiosk.spring.domain.BaseEntity;
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Product extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String productNumber;
//
//    @Enumerated(EnumType.STRING)
//    private ProductType type;
//
//    @Enumerated(EnumType.STRING)
//    private ProductSellingType sellingStatus;
//
//    private String name;
//
//    private int price;
//
//    @Builder
//    private Product(String productNumber, ProductType type, ProductSellingType sellingStatus, String name, int price) {
//        this.productNumber = productNumber;
//        this.type = type;
//        this.sellingStatus = sellingStatus;
//        this.name = name;
//        this.price = price;
//    }
//}
