package com.bil.erp.intefaces.service;

import com.bil.erp.dto.order.OrderProductRequest;
import com.bil.erp.model.OrderProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderProductService {

    OrderProduct create (Long orderId, OrderProductRequest request);

    OrderProduct update(OrderProductRequest request, Long orderId, Long orderProductId);

    void delete(Long orderProductId);

    Page<OrderProduct> getAll(Pageable pageable);
}
