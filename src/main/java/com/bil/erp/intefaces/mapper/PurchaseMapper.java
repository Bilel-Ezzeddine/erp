package com.bil.erp.intefaces.mapper;

import com.bil.erp.dto.purchase.PurchaseRequest;
import com.bil.erp.dto.purchase.PurchaseResponse;
import com.bil.erp.model.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PurchaseMapper {

    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "id", ignore = true)
    Purchase toEntity(PurchaseRequest request);

    PurchaseResponse toResponse(Purchase purchase);
}
