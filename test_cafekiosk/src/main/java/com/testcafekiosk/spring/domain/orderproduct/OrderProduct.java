//package com.testcafekiosk.spring.domain.orderproduct;
//
//import com.testcafekiosk.spring.domain.BaseEntity;
//import com.testcafekiosk.spring.domain.product.Product;
//import com.testcafekiosk.spring.domain.order.Order;
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
//public class OrderProduct extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Order order;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Product product;
//
//    public OrderProduct(Order order, Product product) {
//        this.order = order;
//        this.product = product;
//    }
//
//}
