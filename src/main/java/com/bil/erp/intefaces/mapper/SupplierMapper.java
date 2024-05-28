package com.bil.erp.intefaces.mapper;

import com.bil.erp.dto.supplier.SupplierRequest;
import com.bil.erp.dto.supplier.SupplierResponse;
import com.bil.erp.model.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SupplierMapper {

    @Mapping(target = "purchases", ignore = true)
    @Mapping(target = "id", ignore = true)
    Supplier toEntity(SupplierRequest request);

    SupplierResponse toResponse(Supplier supplier);
}
