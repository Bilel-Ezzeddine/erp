package com.bil.erp.service;

import com.bil.erp.dto.order.OrderRequest;
import com.bil.erp.intefaces.repository.OrderRepository;
import com.bil.erp.intefaces.service.ClientService;
import com.bil.erp.intefaces.service.OrderService;
import com.bil.erp.model.Client;
import com.bil.erp.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ClientService clientService;
    private final OrderRepository orderRepository;

    @Override
    public Order create() {
        Order order = new Order();
        order.setOrderDate(Instant.now());
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateClient(Long orderId, OrderRequest request) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        Client client = clientService.getById(request.getClientId());
        order.setClient(client);
        return order;
    }

    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    @Override
    public Page<Order> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public void delete(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
