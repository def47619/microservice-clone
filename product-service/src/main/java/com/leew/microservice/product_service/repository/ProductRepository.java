package com.leew.microservice.product_service.repository;

import com.leew.microservice.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 조심할 것, MongoDB와 연결된 ORM을 사용하려면 JPA가 아니라
 * MongoRepository를 사용해야 함
 */
public interface ProductRepository extends MongoRepository<Product, String> {
}
