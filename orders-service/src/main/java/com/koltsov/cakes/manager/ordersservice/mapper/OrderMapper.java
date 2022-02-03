package com.koltsov.cakes.manager.ordersservice.mapper;

import com.koltsov.cakes.manager.mapper.CakeMangerMapperConfig;
import com.koltsov.cakes.manager.mapper.GenericMapper;
import com.koltsov.cakes.manager.ordersservice.data.Order;
import com.koltsov.cakes.manager.service.DataEnhancer;
import com.koltsov.cakes.manager.web.dto.cake.CakeDto;
import com.koltsov.cakes.manager.web.dto.order.OrderCreateDto;
import com.koltsov.cakes.manager.web.dto.order.OrderDto;
import com.koltsov.cakes.manager.web.dto.user.UserDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = CakeMangerMapperConfig.class)
public abstract class OrderMapper implements GenericMapper<Order, OrderDto, OrderCreateDto> {

    @Autowired
    private DataEnhancer<CakeDto, Long> cakeDataEnhancer;
    @Autowired
    private DataEnhancer<UserDto, Long> userDataEnhancer;

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "cake", ignore = true)
    public abstract OrderDto toDto(Order entity);

    @AfterMapping
    void afterToDtoMapping(Order entity, @MappingTarget OrderDto orderDto) {
        orderDto.setCake(cakeDataEnhancer.enhance(entity.getCakeId()));
        orderDto.setUser(userDataEnhancer.enhance(entity.getUserId()));
    }

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "cakeId", source = "cake.id")
    @Override
    public abstract Order toEntity(OrderDto dto);

    @Mapping(target = "id", ignore = true)
    @Override
    public abstract Order toNewEntity(OrderCreateDto createDto);
}
