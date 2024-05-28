package com.bil.erp.intefaces.repository;

import com.bil.erp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
