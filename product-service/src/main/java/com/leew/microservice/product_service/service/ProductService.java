package com.leew.microservice.product_service.service;

import com.leew.microservice.product_service.dto.ProductRequest;
import com.leew.microservice.product_service.dto.ProductResponse;
import com.leew.microservice.product_service.model.Product;
import com.leew.microservice.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    // RequiredArgsConstructor + private final -> 생성자 주입
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        Product savedProduct = productRepository.save(product);
        log.info("Product created Successfully: " + product);
        return new ProductResponse(
                product.getId(), product.getName(), product.getDescription(), product.getPrice()
        );
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(), product.getName(), product.getDescription(), product.getPrice()
                )).toList();
    }
}
