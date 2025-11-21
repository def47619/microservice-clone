package com.leew.microservice.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * @Document는 Spring Data MongoDB에서 사용하는 어노테이션으로,
 * 해당 클래스가 MongoDB의 컬렉션(Collection)과 매핑되는 도메인 객체임을 의미
 *
 * Entity -> JPA에서 테이블 의미
 * Document -> MongoDB에서 컬렉션을 의미 :: 하나의 document로 저장/조회됨
 *
 * Spring Data MongoDB가 Java 필드를 기반으로 Document를 구성하여 저장함
 */
@Document(value="product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;

}
