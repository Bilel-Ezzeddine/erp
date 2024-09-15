package com.bil.erp.service;

import com.bil.erp.dto.product.ProductFilterCriteria;
import com.bil.erp.intefaces.mapper.ProductMapper;
import com.bil.erp.intefaces.repository.CategoryRepository;
import com.bil.erp.intefaces.repository.ProductRepository;
import com.bil.erp.intefaces.service.ProductService;
import com.bil.erp.model.Product;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public Product create(Product product) {
        var category = categoryRepository.findById(product.getCategory().getId());
        product.setCategory(category.orElseThrow());
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> searchByFilterCriteria(ProductFilterCriteria filter, Pageable pageable) {
        return productRepository.findAll((Specification<Product>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getName() != null && !filter.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + filter.getName().toLowerCase() + "%"
                ));
            }
            if (filter.getPrice() != null && !filter.getPrice().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("price").as(String.class)),
                        "%" + filter.getPrice().toLowerCase() + "%"
                ));
            }
            if (filter.getQuantity() != null && !filter.getQuantity().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("quantity").as(String.class)),
                        "%" + filter.getQuantity().toLowerCase() + "%"
                ));
            }
            if (filter.getBarcode() != null && !filter.getBarcode().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("barcode")),
                        "%" + filter.getBarcode().toLowerCase() + "%"
                ));
            }
            if (filter.getCategoryName() != null && !filter.getCategoryName().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("category").get("name")),
                        "%" + filter.getCategoryName().toLowerCase() + "%"
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
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


