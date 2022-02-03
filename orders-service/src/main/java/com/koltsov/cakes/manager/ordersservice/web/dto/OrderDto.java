package com.koltsov.cakes.manager.ordersservice.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private Long cakeId;
    private Long userId;
    private LocalDateTime deliveryDate;
}
