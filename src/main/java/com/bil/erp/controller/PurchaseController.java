package com.bil.erp.controller;

import com.bil.erp.dto.purchase.PurchaseRequest;
import com.bil.erp.dto.purchase.PurchaseResponse;
import com.bil.erp.dto.supplier.SupplierResponse;
import com.bil.erp.intefaces.mapper.PurchaseMapper;
import com.bil.erp.intefaces.mapper.SupplierMapper;
import com.bil.erp.intefaces.service.PurchaseService;
import com.bil.erp.model.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/supplier/{supplierId}/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final PurchaseMapper purchaseMapper;
    private final SupplierMapper supplierMapper;

    @GetMapping
    public ResponseEntity<Page<PurchaseResponse>> getAll(Pageable pageable) {
        Page<Purchase> purchases = purchaseService.getAll(pageable);
        Page<PurchaseResponse> purchaseResponses = purchases.map(purchaseMapper::toResponse);
        return ResponseEntity.ok(purchaseResponses);
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> create(@RequestBody final PurchaseRequest request) {
        Purchase purchase = purchaseService.create(request);
        return ResponseEntity.ok(supplierMapper.toResponse(purchase.getSupplier()));
    }

    @PutMapping("/{purchaseId}")
    public ResponseEntity<SupplierResponse> update(@RequestBody final PurchaseRequest request,
                                                @PathVariable Long purchaseId) {
        Purchase purchase = purchaseService.update(request, purchaseId);
        SupplierResponse response = supplierMapper.toResponse(purchase.getSupplier());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/purchaseId")
    public void delete(@PathVariable Long purchaseId) {
        purchaseService.delete(purchaseId);
    }
}
