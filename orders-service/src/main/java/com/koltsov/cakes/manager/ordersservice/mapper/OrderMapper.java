package com.koltsov.cakes.manager.ordersservice.mapper;

import com.koltsov.cakes.manager.client.CakeClient;
import com.koltsov.cakes.manager.client.UserClient;
import com.koltsov.cakes.manager.mapper.CakeMangerMapperConfig;
import com.koltsov.cakes.manager.mapper.GenericMapper;
import com.koltsov.cakes.manager.ordersservice.data.Order;
import com.koltsov.cakes.manager.web.dto.order.OrderCreateDto;
import com.koltsov.cakes.manager.web.dto.order.OrderDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = CakeMangerMapperConfig.class)
public abstract class OrderMapper implements GenericMapper<Order, OrderDto, OrderCreateDto> {

    @Autowired
    private CakeClient cakeClient;
    @Autowired
    private UserClient userClient;

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "cake", ignore = true)
    public abstract OrderDto toDto(Order entity);

    @AfterMapping
    void afterToDtoMapping(Order entity, @MappingTarget OrderDto orderDto) {
        orderDto.setCake(cakeClient.getCakeById(entity.getCakeId()));
        orderDto.setUser(userClient.getUserById(entity.getUserId()));
    }

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "cakeId", source = "cake.id")
    @Override
    public abstract Order toEntity(OrderDto dto);

    @Mapping(target = "id", ignore = true)
    @Override
    public abstract Order toNewEntity(OrderCreateDto createDto);
}
