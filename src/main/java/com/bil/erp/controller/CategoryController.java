package com.bil.erp.controller;

import com.bil.erp.dto.category.CategoryFilterCriteria;
import com.bil.erp.dto.category.CategoryRequest;
import com.bil.erp.dto.category.CategoryResponse;
import com.bil.erp.intefaces.mapper.CategoryMapper;
import com.bil.erp.intefaces.service.CategoryService;
import com.bil.erp.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping()
    public ResponseEntity<Page<CategoryResponse>> getAll(Pageable pageable) {
        Page<Category> categories = categoryService.getAll(pageable);
        Page<CategoryResponse> categoryResponses = categories.map(categoryMapper::toResponse);
        return ResponseEntity.ok(categoryResponses);
    }

    @PostMapping("/filter")
    public ResponseEntity<Page<CategoryResponse>> searchByFilterCriteria(@RequestBody CategoryFilterCriteria filter, Pageable pageable) {
        Page<Category> filteredCategories = categoryService.searchByFilterCriteria(filter, pageable);
        Page<CategoryResponse> categoryResponse = filteredCategories.map(categoryMapper::toResponse);
        return ResponseEntity.ok(categoryResponse);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody final CategoryRequest request) {
        var ct = categoryService.create(categoryMapper.toEntity(request));
        return ResponseEntity.ok(categoryMapper.toResponse(ct));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> update(@PathVariable final Long categoryId, @RequestBody CategoryRequest request) {
        var ct = categoryMapper.toEntity(request);
        var category = categoryService.update(categoryId, ct);
        return ResponseEntity.ok(categoryMapper.toResponse(category));
    }

    @GetMapping("/{categoryId}")
    private ResponseEntity<CategoryResponse> getById(@PathVariable final Long categoryId) {
        final Category category = categoryService.getById(categoryId);
        return ResponseEntity.ok(categoryMapper.toResponse(category));
    }

    @DeleteMapping("/{categoryId}")
    private void delete(@PathVariable final Long categoryId) {
        categoryService.delete(categoryId);
    }
}
