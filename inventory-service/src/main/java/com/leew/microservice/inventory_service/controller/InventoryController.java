package com.leew.microservice.inventory_service.controller;

import com.leew.microservice.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/exists")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam(value = "skuCode") String skuCode, @RequestParam(value = "quantity") Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }
}
