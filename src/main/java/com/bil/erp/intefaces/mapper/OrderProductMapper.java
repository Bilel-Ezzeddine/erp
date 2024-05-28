package com.bil.erp.intefaces.mapper;

import com.bil.erp.dto.order.OrderProductRequest;
import com.bil.erp.dto.order.OrderProductResponse;
import com.bil.erp.model.OrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderProductMapper {

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "id", ignore = true)
    OrderProduct toEntity(OrderProductRequest request);

    OrderProductResponse toResponse(OrderProduct orderProduct);
}
