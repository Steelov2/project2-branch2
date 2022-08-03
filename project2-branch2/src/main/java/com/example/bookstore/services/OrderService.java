package com.example.bookstore.services;

import com.example.bookstore.DTOs.order.OrderCreateDto;
import com.example.bookstore.DTOs.order.OrderRequestDto;
import com.example.bookstore.DTOs.order.OrderUpdateDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderCreateDto create(OrderCreateDto orderCreateDto) throws Exception;
    void update(OrderUpdateDto orderUpdateDto) throws Exception;
    void deleteById(Long id);
    List<OrderRequestDto> getAll();
    Optional<OrderRequestDto> getByID(Long id);
}
