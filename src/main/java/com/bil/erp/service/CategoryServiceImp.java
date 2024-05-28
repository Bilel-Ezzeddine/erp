package com.bil.erp.service;

import com.bil.erp.intefaces.service.CategoryService;
import com.bil.erp.model.Category;
import com.bil.erp.intefaces.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}


