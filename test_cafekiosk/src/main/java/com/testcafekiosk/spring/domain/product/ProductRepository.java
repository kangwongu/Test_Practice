//package com.testcafekiosk.spring.domain.product;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface ProductRepository extends JpaRepository<Product, Long> {
//
//    /**
//     * select *
//     * from product
//     * where selling_status in ('SELLING', 'HOLD');
//     */
//    List<Product> findAllBySellingStatusIn(List<ProductSellingType> sellingStatuses);
//    List<Product> findAllByProductNumberIn(List<String> productNumbers);
//}
