package com.koltsov.cakes.manager.client;

import com.koltsov.cakes.manager.web.dto.order.OrderCreateDto;
import com.koltsov.cakes.manager.web.dto.order.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "ORDERS-SERVICE", path = "/api/v1/orders")
public interface OrderClient {

    @GetMapping
    List<OrderDto> getAllOrders();

    @GetMapping("{orderId}")
    OrderDto getOrderById(@PathVariable("orderId") Long orderId);

    @PostMapping
    OrderDto createOrder(@RequestBody OrderCreateDto OrderCreateDto);

    @PutMapping("{orderId}")
    OrderDto updateOrderById(@PathVariable("orderId") Long orderId, @RequestBody OrderDto OrderDto);

    @DeleteMapping("{orderId}")
    void deleteOrderById(@PathVariable("orderId") Long orderId);
}
