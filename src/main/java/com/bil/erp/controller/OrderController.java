package com.bil.erp.controller;

import com.bil.erp.dto.order.OrderRequest;
import com.bil.erp.dto.order.OrderResponse;
import com.bil.erp.intefaces.mapper.OrderMapper;
import com.bil.erp.intefaces.service.OrderService;
import com.bil.erp.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Tag(name = "order")
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;


    @GetMapping
    @Operation(operationId = "orderGetAll")
    public ResponseEntity<Page<OrderResponse>> getAll(Pageable pageable) {
        Page<Order> orders = orderService.getAll(pageable);
        Page<OrderResponse> responses = orders.map(orderMapper::toResponse);
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    @Operation(operationId = "orderCreate")
    public ResponseEntity<OrderResponse> create() {
        Order order = orderService.create();
        OrderResponse response = orderMapper.toResponse(order);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{orderId}")
    @Operation(operationId = "orderUpdate")
    public ResponseEntity<OrderResponse> update(@PathVariable Long orderId,
                                                      @RequestBody OrderRequest request){
        Order order = orderService.updateClient(orderId, request);
        OrderResponse response = orderMapper.toResponse(order);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{orderId}")
    @Operation(operationId = "orderDelete")
    public void delete(@PathVariable Long orderId){
        orderService.delete(orderId);
    }
}
