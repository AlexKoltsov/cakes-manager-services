package com.koltsov.cakes.manager.ordersservice.web.controller;

import com.koltsov.cakes.manager.mapper.GenericMapper;
import com.koltsov.cakes.manager.ordersservice.data.Order;
import com.koltsov.cakes.manager.ordersservice.web.dto.OrderCreateDto;
import com.koltsov.cakes.manager.ordersservice.web.dto.OrderDto;
import com.koltsov.cakes.manager.service.CrudService;
import com.koltsov.cakes.manager.web.controller.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController extends CrudController<Order, Long, OrderDto, OrderCreateDto> {

    public OrderController(CrudService<Order, Long> crudService,
                           GenericMapper<Order, OrderDto, OrderCreateDto> mapper) {
        super(crudService, mapper);
    }
}
