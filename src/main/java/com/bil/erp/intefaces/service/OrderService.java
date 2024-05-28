package com.bil.erp.intefaces.service;

import com.bil.erp.dto.order.OrderRequest;
import com.bil.erp.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Order create();

    Order updateClient(Long orderId, OrderRequest request);

    Order findById(Long orderId);

    Page<Order> getAll(Pageable pageable);

    void delete(Long orderId);
}
