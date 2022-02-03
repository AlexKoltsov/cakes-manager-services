package com.koltsov.cakes.manager.ordersservice.repository;

import com.koltsov.cakes.manager.ordersservice.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
