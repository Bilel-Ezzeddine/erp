package com.bil.erp.service;

import com.bil.erp.intefaces.repository.SupplierRepository;
import com.bil.erp.intefaces.service.SupplierService;
import com.bil.erp.model.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    @Override
    public Page<Supplier> getAll(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

    @Override
    public Supplier getById(Long supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow();
    }

    @Override
    public Supplier create(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier update(Long supplierId, Supplier supplier) {
        var s = supplierRepository.findById(supplierId).orElseThrow();
        s.setCity(supplier.getCity());
        s.setAddress(supplier.getAddress());
        s.setCountry(supplier.getCountry());
        s.setEmail(supplier.getEmail());
        s.setGsmPhone(supplier.getGsmPhone());
        s.setPhone(supplier.getPhone());
        s.setZipCode(supplier.getZipCode());
        s.setPurchases(supplier.getPurchases());
        return supplierRepository.save(s);
    }

    @Override
    public void delete(Long supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
