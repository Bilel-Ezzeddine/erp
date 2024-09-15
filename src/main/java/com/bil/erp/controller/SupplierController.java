package com.bil.erp.controller;

import com.bil.erp.dto.supplier.SupplierRequest;
import com.bil.erp.dto.supplier.SupplierResponse;
import com.bil.erp.intefaces.mapper.SupplierMapper;
import com.bil.erp.intefaces.service.SupplierService;
import com.bil.erp.model.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;


    @GetMapping
    public ResponseEntity<Page<SupplierResponse>> getAll(Pageable pageable) {
        Page<Supplier> suppliers = supplierService.getAll(pageable);
        Page<SupplierResponse> responses = suppliers.map(supplierMapper::toResponse);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierResponse> getById(@PathVariable final Long supplierId) {
        final Supplier supplier = supplierService.getById(supplierId);
        return ResponseEntity.ok(supplierMapper.toResponse(supplier));
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> create(@RequestBody final SupplierRequest request) {
        var supp = supplierService.create(supplierMapper.toEntity(request));
        return ResponseEntity.ok(supplierMapper.toResponse(supp));
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<SupplierResponse> update(@PathVariable final Long supplierId, @RequestBody SupplierRequest request) {
        var supp = supplierMapper.toEntity(request);
        var supplier = supplierService.update(supplierId, supp);
        return ResponseEntity.ok(supplierMapper.toResponse(supplier));
    }

    @DeleteMapping("/{supplierId}")
    private void delete(@PathVariable final Long supplierId) {
        supplierService.delete(supplierId);
    }
}
