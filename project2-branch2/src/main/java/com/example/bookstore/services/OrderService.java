package com.example.bookstore.services;

import com.example.bookstore.DTOs.order.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDto create(OrderDto orderDTO);
    void update(OrderDto orderDTO, Long id);
    void deleteById(Long id);
    List<OrderDto> getAll();
    Optional<OrderDto> getByID(Long id);
}
