package com.leew.microservice.inventory_service.service;

import com.leew.microservice.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    /**
     * 이미 데이터는 flyway 적용한 V2__add_inventory.sql 파일에서 넣었으므로
     * 새로운 코드를 생성하지는 않는다.
     */

    public boolean isInStock(String skuCode, Integer quantity) {
        /**
         * find an inventory for a given skuCode
         * where quantity > 0
         */
        return inventoryRepository.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }
}
