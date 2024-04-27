//package com.testcafekiosk.spring.api.service;
//
//import com.testcafekiosk.spring.api.service.product.response.ProductResponse;
//import com.testcafekiosk.spring.domain.product.Product;
//import com.testcafekiosk.spring.domain.product.ProductRepository;
//import com.testcafekiosk.spring.domain.product.ProductSellingType;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class ProductService {
//
//    private final ProductRepository productRepository;
//
//    public List<ProductResponse> getSellingProducts() {
//        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingType.forDisplay());
//
//        return products.stream()
//                .map(ProductResponse::of)
//                .collect(Collectors.toList());
//    }
//}
