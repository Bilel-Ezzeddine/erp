package com.bil.erp.intefaces.service;

import com.bil.erp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Category create(Category category);

    Page<Category> getAll(Pageable pageable);

    Category update(Long categoryId, Category category);

    void delete(Long categoryId);

    Category getById(Long categoryId);
}
