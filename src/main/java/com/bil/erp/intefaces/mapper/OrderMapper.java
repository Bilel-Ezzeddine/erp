package com.bil.erp.intefaces.mapper;

import com.bil.erp.dto.order.OrderRequest;
import com.bil.erp.dto.order.OrderResponse;
import com.bil.erp.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ClientMapper.class, OrderProductMapper.class})
public interface OrderMapper {

    @Mapping(target = "orderTotal", ignore = true)
    @Mapping(target = "orderProducts", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    Order toEntity(OrderRequest request);

    @Mapping(target = "products", source = "orderProducts")
    OrderResponse toResponse(Order order);
}
