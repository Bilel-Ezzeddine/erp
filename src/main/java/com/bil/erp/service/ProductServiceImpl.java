package com.bil.erp.service;

import com.bil.erp.intefaces.mapper.ProductMapper;
import com.bil.erp.intefaces.service.ProductService;
import com.bil.erp.model.Product;
import com.bil.erp.intefaces.repository.CategoryRepository;
import com.bil.erp.intefaces.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public Product create (Product product){
        var category = categoryRepository.findById(product.getCategory().getId());
        product.setCategory(category.orElseThrow());
        return productRepository.save(product);
    }
    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    @Override
    @Transactional
    public Product update(Long productId, Product product) {
        var productDB = productRepository.findById(productId).orElseThrow();
        productMapper.partialUpdate(product, productDB);
        return productDB;
    }
    @Override
    @Transactional
    public void delete(Long categoryId) {
        productRepository.deleteById(categoryId);
    }

    @Override
    public Product getById(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }
}


