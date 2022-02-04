package com.koltsov.cakes.manager.web.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderCreateDto {
    private Long cakeId;
    private Long userId;
    private LocalDateTime deliveryDate;
}
