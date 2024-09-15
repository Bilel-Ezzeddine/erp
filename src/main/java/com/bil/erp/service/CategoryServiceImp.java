package com.bil.erp.service;

import com.bil.erp.dto.category.CategoryFilterCriteria;
import com.bil.erp.intefaces.repository.CategoryRepository;
import com.bil.erp.intefaces.service.CategoryService;
import com.bil.erp.model.Category;
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
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category create(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    @Transactional(readOnly = true)
    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
    @Override
    @Transactional
    public Category update(Long categoryId, Category category) {
        var c = categoryRepository.findById(categoryId).orElseThrow();
        c.setName(category.getName());
        return categoryRepository.save(c);
    }
    @Override
    @Transactional
    public void delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category getById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow();
    }

    @Override
    public Page<Category> searchByFilterCriteria(CategoryFilterCriteria filter, Pageable pageable) {
        return categoryRepository.findAll((Specification<Category>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getName() != null && !filter.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + filter.getName().toLowerCase() + "%"
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }
}


