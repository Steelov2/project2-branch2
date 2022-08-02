package com.example.bookstore.services;

import com.example.bookstore.DTOs.order.OrderDto;
import com.example.bookstore.DTOs.order.OrderUpdateDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDto create(OrderDto orderDTO);
    void update(OrderUpdateDto orderUpdateDto);
    void deleteById(Long id);
    List<OrderDto> getAll();
    Optional<OrderDto> getByID(Long id);
}
