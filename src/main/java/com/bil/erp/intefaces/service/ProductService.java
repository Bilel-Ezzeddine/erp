package com.bil.erp.intefaces.service;

import com.bil.erp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product create(Product product);

    Page<Product> getAll(Pageable pageable);
    Product update(Long productId, Product product);
    void delete(Long categoryId);

    Product getById(Long productId);
}
