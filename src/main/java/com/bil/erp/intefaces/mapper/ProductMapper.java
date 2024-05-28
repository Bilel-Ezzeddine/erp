package com.bil.erp.intefaces.mapper;

import com.bil.erp.dto.product.ProductRequest;
import com.bil.erp.dto.product.ProductResponse;
import com.bil.erp.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mapping(target = "purchases", ignore = true)
    @Mapping(target = "orderProducts", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductRequest productRequest);

    ProductResponse toResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderProducts", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    void partialUpdate(Product source, @MappingTarget Product target);
}
