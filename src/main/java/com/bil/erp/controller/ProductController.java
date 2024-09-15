package com.bil.erp.controller;

import com.bil.erp.dto.product.ProductFilterCriteria;
import com.bil.erp.dto.product.ProductRequest;
import com.bil.erp.dto.product.ProductResponse;
import com.bil.erp.intefaces.mapper.ProductMapper;
import com.bil.erp.intefaces.service.ProductService;
import com.bil.erp.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAll(Pageable pageable) {
        Page<Product> categories = productService.getAll(pageable);
        Page<ProductResponse> categoryResponses = categories.map(productMapper::toResponse);
        return ResponseEntity.ok(categoryResponses);
    }

    @PostMapping("/filter")
    public ResponseEntity<Page<ProductResponse>> searchByFilterCriteria(@RequestBody ProductFilterCriteria filter, Pageable pageable) {
        Page<Product> filteredProducts = productService.searchByFilterCriteria(filter, pageable);
        Page<ProductResponse> productResponses = filteredProducts.map(productMapper::toResponse);
        return ResponseEntity.ok(productResponses);
    }


    @GetMapping("/{productId}")
    private ResponseEntity<ProductResponse> getById(@PathVariable final Long productId) {
        final Product product = productService.getById(productId);
        return ResponseEntity.ok(productMapper.toResponse(product));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody final ProductRequest request) {
        var p = productService.create(productMapper.toEntity(request));
        return ResponseEntity.ok(productMapper.toResponse(p));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> update(@PathVariable final Long productId,
                                          @RequestBody ProductRequest request) {
        var prd = productService.update(productId, productMapper.toEntity(request));
        return ResponseEntity.ok(productMapper.toResponse(prd));
    }

    @DeleteMapping("/{productId}")
    private void delete(@PathVariable final Long productId) {
        productService.delete(productId);
    }
}
