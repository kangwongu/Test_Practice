//package com.testcafekiosk.spring.api.service.order;
//
//import com.testcafekiosk.spring.api.controller.order.request.OrderCreateRequest;
//import com.testcafekiosk.spring.api.service.order.response.OrderResponse;
//import com.testcafekiosk.spring.domain.order.Order;
//import com.testcafekiosk.spring.domain.order.OrderRepository;
//import com.testcafekiosk.spring.domain.product.Product;
//import com.testcafekiosk.spring.domain.product.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class OrderService {
//
//    private final ProductRepository productRepository;
//    private final OrderRepository orderRepository;
//
//    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {
//        List<String> productNumbers = request.getProductNumbers();
//        List<Product> duplicateProducts = findProductsBy(productNumbers);
//
//        Order order = Order.create(duplicateProducts, registeredDateTime);
//        order = orderRepository.save(order);
//
//        return OrderResponse.of(order);
//    }
//
//    private List<Product> findProductsBy(List<String> productNumbers) {
//        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
//        Map<String, Product> productMap = products.stream()
//                .collect(Collectors.toMap(product -> product.getProductNumber(), product -> product));
//
//        return productNumbers.stream()
//                .map(productNumber -> productMap.get(productNumber))
//                .collect(Collectors.toList());
//    }
//}
