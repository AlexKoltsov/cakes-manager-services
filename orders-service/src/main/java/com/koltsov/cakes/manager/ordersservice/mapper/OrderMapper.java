package com.koltsov.cakes.manager.ordersservice.mapper;

import com.koltsov.cakes.manager.mapper.CakeMangerMapperConfig;
import com.koltsov.cakes.manager.mapper.GenericMapper;
import com.koltsov.cakes.manager.ordersservice.data.Order;
import com.koltsov.cakes.manager.ordersservice.web.dto.OrderCreateDto;
import com.koltsov.cakes.manager.ordersservice.web.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CakeMangerMapperConfig.class)
public interface OrderMapper extends GenericMapper<Order, OrderDto, OrderCreateDto> {
    @Mapping(target = "id", ignore = true)
    @Override
    Order toNewEntity(OrderCreateDto createDto);
}
