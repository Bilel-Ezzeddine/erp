package com.bil.erp.service;

import com.bil.erp.dto.purchase.PurchaseRequest;
import com.bil.erp.intefaces.repository.PurchaseRepository;
import com.bil.erp.intefaces.service.ProductService;
import com.bil.erp.intefaces.service.PurchaseService;
import com.bil.erp.intefaces.service.SupplierService;
import com.bil.erp.model.Product;
import com.bil.erp.model.Purchase;
import com.bil.erp.model.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final SupplierService supplierService;
    private final ProductService productService;
    @Override
    public Page<Purchase> getAll(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }

    @Override
    public void delete(Long purchaseId) {
        purchaseRepository.deleteById(purchaseId);
    }

    @Override
    public Purchase create(PurchaseRequest request) {
        Supplier supplier = supplierService.getById(request.getSupplierId());
        Product product = productService.getById(request.getProductId());
        Purchase purchase = new Purchase();
        purchase.setProduct(product);
        purchase.setSupplier(supplier);
        purchase.setQuantity(request.getQuantity());
        purchase.setPrice(request.getPrice());
        purchase.setPurchaseDate(request.getPurchaseDate());
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase update(PurchaseRequest request, Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow();
        Supplier supplier = supplierService.getById(request.getSupplierId());
        Product product = productService.getById(request.getProductId());
        purchase.setProduct(product);
        purchase.setSupplier(supplier);
        purchase.setQuantity(request.getQuantity());
        purchase.setPrice(request.getPrice());
        purchase.setPurchaseDate(request.getPurchaseDate());
        return purchaseRepository.save(purchase);
    }
}
