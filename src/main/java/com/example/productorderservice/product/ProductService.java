package com.example.productorderservice.product;

import org.springframework.stereotype.Service;

@Service
class ProductService {
    private final ProductPort productPort;

    ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    public void addProduct(AddProductRequest request) {
        final Product product = new Product(request.getName(), request.getPrice(), request.getDiscountPolicy());

        productPort.save(product);

    }

    public GetProductResponse getProduct(long productId) {
        final Product product = productPort.getProduct(productId);

        return new GetProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDiscountPolicy());
    }

    public void updateProduct(Long productId, UpdateProductRequest request) {
        Product product = productPort.getProduct(productId);
        product.update(request.getName(), request.getPrice(), request.getDiscountPolicy());

        productPort.save(product);
    }
}
