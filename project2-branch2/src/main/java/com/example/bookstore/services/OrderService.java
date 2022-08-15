package com.example.bookstore.services;

import com.example.bookstore.dto.order.OrderCreatDto;
import com.example.bookstore.dto.order.OrderRequestDto;
import com.example.bookstore.dto.order.OrderUpdateForAdmin;
import com.example.bookstore.dto.order.OrderUpdateForUserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void create(OrderCreatDto orderCreatDto) throws Exception;

    void updateForUser(OrderUpdateForUserDto orderUpdateForUserDto);
    void updateForAdmin(OrderUpdateForAdmin orderUpdateForAdmin);

    void deleteById(Long id);
    List<OrderRequestDto> getAll();
    Optional<OrderRequestDto> getByID(Long id);
}
