package com.koltsov.cakes.manager.ordersservice.service;

import com.koltsov.cakes.manager.ordersservice.data.Order;
import com.koltsov.cakes.manager.service.DefaultCrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends DefaultCrudService<Order, Long> {

    public OrderService(JpaRepository<Order, Long> repository) {
        super(repository);
    }

    @Override
    protected void updateFields(Order to, Order from) {
        to.setCakeId(from.getCakeId());
        to.setUserId(from.getUserId());
        to.setDeliveryDate(from.getDeliveryDate());
    }
}
