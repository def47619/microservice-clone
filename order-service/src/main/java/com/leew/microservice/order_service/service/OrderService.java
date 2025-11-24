package com.leew.microservice.order_service.service;

import com.leew.microservice.order_service.dto.OrderRequest;
import com.leew.microservice.order_service.model.Order;
import com.leew.microservice.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        // Map OrderRequest -> Order
        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());

        // save order through OrderRepository
        orderRepository.save(order);

    }
}
