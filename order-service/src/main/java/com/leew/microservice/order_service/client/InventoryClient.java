package com.leew.microservice.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * value: Inventory-service의 application.yml 내 이름
 * spring:
 *   application:
 *     name: inventory-service // 이거
 *
 *  나중에 eureka 서버 사용하게 되면, 정적 url 없이 사용하게 될 것
 *  url 키워드는 삭제할 예정
 */
@FeignClient(value = "inventory-service", url = "${inventory.url}")
public interface InventoryClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory/exists")
    boolean isInStock(@RequestParam(value = "skuCode") String skuCode, @RequestParam(value = "quantity") Integer quantity);
}
