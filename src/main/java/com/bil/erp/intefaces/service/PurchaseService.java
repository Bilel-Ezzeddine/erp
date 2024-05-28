package com.bil.erp.intefaces.service;

import com.bil.erp.dto.purchase.PurchaseRequest;
import com.bil.erp.model.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {
    Page<Purchase> getAll(Pageable pageable);

    void delete(Long purchaseId);

    Purchase create(PurchaseRequest request);

    Purchase update(PurchaseRequest request, Long purchaseId);
}
