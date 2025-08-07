package com.example.orders.service;

import com.example.orders.messaging.OrderEventPublisher;
import com.example.orders.model.Order;
import com.example.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderEventPublisher publisher;

    public OrderService(OrderRepository repository, OrderEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public Order createOrder(Order order) {
        Order saved = repository.save(order);
        publisher.publishOrderCreated(saved);
        return saved;
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }
}
