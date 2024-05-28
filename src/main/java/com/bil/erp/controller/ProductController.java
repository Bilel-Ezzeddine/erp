package com.bil.erp.controller;

import com.bil.erp.dto.product.ProductRequest;
import com.bil.erp.dto.product.ProductResponse;
import com.bil.erp.intefaces.mapper.ProductMapper;
import com.bil.erp.intefaces.service.ProductService;
import com.bil.erp.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Tag(name = "product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    @Operation(operationId = "productGetAll")
    public ResponseEntity<Page<ProductResponse>> getAll(Pageable pageable) {
        Page<Product> categories = productService.getAll(pageable);
        Page<ProductResponse> categoryResponses = categories.map(productMapper::toResponse);
        return ResponseEntity.ok(categoryResponses);
    }

    @GetMapping("/{productId}")
    @Operation(operationId = "productGetById")
    private ResponseEntity<ProductResponse> getById(@PathVariable final Long productId) {
        final Product product = productService.getById(productId);
        return ResponseEntity.ok(productMapper.toResponse(product));
    }

    @PostMapping
    @Operation(operationId = "productCreate")
    public ResponseEntity<Product> create(@RequestBody final ProductRequest request) {
        var p = productService.create(productMapper.toEntity(request));
        return ResponseEntity.ok(p);
    }

    @PutMapping("/{productId}")
    @Operation(operationId = "productUpdate")
    public ResponseEntity<Product> update(@PathVariable final Long productId,
                                          @RequestBody ProductRequest request) {
        var prd = productService.update(productId, productMapper.toEntity(request));
        return ResponseEntity.ok(prd);
    }

    @DeleteMapping("/{productId}")
    @Operation(operationId = "productDelete")
    private void delete(@PathVariable final Long productId) {
        productService.delete(productId);
    }
}
