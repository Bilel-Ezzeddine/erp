package com.bil.erp.service;

import com.bil.erp.dto.order.OrderProductRequest;
import com.bil.erp.intefaces.repository.OrderProductRepository;
import com.bil.erp.intefaces.service.OrderProductService;
import com.bil.erp.intefaces.service.OrderService;
import com.bil.erp.intefaces.service.ProductService;
import com.bil.erp.model.Order;
import com.bil.erp.model.OrderProduct;
import com.bil.erp.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderProductRepository orderProductRepository;

    @Override
    @Transactional
    public OrderProduct create(Long orderId, OrderProductRequest request) {
        Order order = orderService.findById(orderId);
        Product product = productService.getById(request.getProductId());
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrder(order);
        orderProduct.setQuantity(request.getQuantity());
        orderProduct.setSalePrice(request.getSalePrice());
        order.setOrderTotal(order.getOrderTotal() + orderProduct.getQuantity()
                * orderProduct.getSalePrice());
        return orderProductRepository.save(orderProduct);
    }

    @Override
    @Transactional
    public OrderProduct update(OrderProductRequest request, Long orderId, Long orderProductId) {
        OrderProduct orderProduct = orderProductRepository.findById(orderProductId).orElseThrow();
        Order order = orderService.findById(orderId);
        Product product = productService.getById(request.getProductId());
        orderProduct.setProduct(product);
        orderProduct.setOrder(order);
        orderProduct.setQuantity(request.getQuantity());
        orderProduct.setSalePrice(request.getSalePrice());
        order.setOrderTotal(order.getOrderTotal() + orderProduct.getQuantity()
                * orderProduct.getSalePrice());
        return orderProductRepository.save(orderProduct);

    }

    @Override
    public void delete(Long orderProductId) {
        orderProductRepository.deleteById(orderProductId);
    }

    @Override
    public Page<OrderProduct> getAll(Pageable pageable) {
        return orderProductRepository.findAll(pageable);
    }
}
