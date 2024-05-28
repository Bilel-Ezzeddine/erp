package com.bil.erp.controller;

import com.bil.erp.dto.order.OrderProductRequest;
import com.bil.erp.dto.order.OrderProductResponse;
import com.bil.erp.dto.order.OrderResponse;
import com.bil.erp.intefaces.mapper.OrderMapper;
import com.bil.erp.intefaces.mapper.OrderProductMapper;
import com.bil.erp.intefaces.service.OrderProductService;
import com.bil.erp.model.OrderProduct;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/order/{orderId}/product")
@RequiredArgsConstructor
@Tag(name = "orderProduct")
public class OrderProductController {

    private final OrderProductService orderProductService;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;

    @GetMapping
    @Operation(operationId = "orderProductGetAll")
    public ResponseEntity<Page<OrderProductResponse>> getAll(Pageable pageable) {
        Page<OrderProduct> products = orderProductService.getAll(pageable);
        Page<OrderProductResponse> responses = products.map(orderProductMapper::toResponse);
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    @Operation(operationId = "orderProductCreate")
    public ResponseEntity<OrderResponse> create(@RequestBody final OrderProductRequest request,
                                                @PathVariable Long orderId) {
        OrderProduct orderProduct = orderProductService.create(orderId, request);
        return ResponseEntity.ok(orderMapper.toResponse(orderProduct.getOrder()));
    }

    @PutMapping("/{orderProductId}")
    @Operation(operationId = "orderProductUpdate")
    public ResponseEntity<OrderResponse> update(@RequestBody final OrderProductRequest request,
                                                @PathVariable Long orderId,
                                                @PathVariable Long orderProductId) {
        OrderProduct orderProduct = orderProductService.update(request, orderId, orderProductId);
        OrderResponse response = orderMapper.toResponse(orderProduct.getOrder());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/orderProductId")
    @Operation(operationId = "orderProductDelete")
    public void delete(@PathVariable Long orderProductId) {
        orderProductService.delete(orderProductId);
    }
}
