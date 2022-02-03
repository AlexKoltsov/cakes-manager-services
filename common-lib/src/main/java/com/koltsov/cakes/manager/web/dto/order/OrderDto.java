package com.koltsov.cakes.manager.web.dto.order;

import com.koltsov.cakes.manager.web.dto.cake.CakeDto;
import com.koltsov.cakes.manager.web.dto.user.UserDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private CakeDto cake;
    private UserDto user;
    private LocalDateTime deliveryDate;
}
