package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.order.OrderCreateDto;
import com.example.bookstore.DTOs.order.OrderUpdateForAdmin;
import com.example.bookstore.DTOs.order.OrderUpdateForUserDto;
import com.example.bookstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/orders")
public class OrderUserController {
    private OrderService orderService;
    @Autowired
    public OrderUserController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PutMapping("/userUpdateOrder")
    public void update(@RequestBody OrderUpdateForUserDto orderUpdateForUserDto) throws Exception {

        orderService.updateForUser(orderUpdateForUserDto);
    }
    @PostMapping("/saveOrder")
    public OrderCreateDto create(@RequestBody OrderCreateDto orderCreateDto) throws Exception {
        return orderService.create(orderCreateDto);
    }

}
