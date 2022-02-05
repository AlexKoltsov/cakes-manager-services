package com.koltsov.cakes.manager.client;

import com.koltsov.cakes.manager.web.dto.order.OrderCreateDto;
import com.koltsov.cakes.manager.web.dto.order.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "ORDERS-SERVICE", path = "/api/v1/orders")
public interface OrderClient {

    @GetMapping
    Page<OrderDto> getAllOrders(@RequestBody Pageable pageable);

    @GetMapping("{orderId}")
    OrderDto getOrderById(@PathVariable("orderId") Long orderId);

    @PostMapping
    OrderDto createOrder(@RequestBody OrderCreateDto OrderCreateDto);

    @PutMapping("{orderId}")
    OrderDto updateOrderById(@PathVariable("orderId") Long orderId, @RequestBody OrderDto OrderDto);

    @DeleteMapping("{orderId}")
    void deleteOrderById(@PathVariable("orderId") Long orderId);
}
