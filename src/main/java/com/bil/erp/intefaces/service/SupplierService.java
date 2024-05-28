package com.bil.erp.intefaces.service;

import com.bil.erp.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {
    Page<Supplier> getAll(Pageable pageable);

    Supplier getById(Long supplierId);

    Supplier create(Supplier supplier);

    Supplier update(Long supplierId, Supplier supplier);

    void delete(Long supplierId);
}
