package com.example.bookstore.services;

import com.example.bookstore.DTOs.order.OrderCreateDto;
import com.example.bookstore.DTOs.order.OrderRequestDto;
import com.example.bookstore.DTOs.order.OrderUpdateForAdmin;
import com.example.bookstore.DTOs.order.OrderUpdateForUserDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderCreateDto create(OrderCreateDto orderCreateDto) throws Exception;

    void updateForUser(OrderUpdateForUserDto orderUpdateForUserDto);
    void updateForAdmin(OrderUpdateForAdmin orderUpdateForAdmin);

    void deleteById(Long id);
    List<OrderRequestDto> getAll();
    Optional<OrderRequestDto> getByID(Long id);
}
