package com.example.bookstore.controllers;
import com.example.bookstore.DTOs.order.OrderCreateDto;
import com.example.bookstore.DTOs.order.OrderRequestDto;
import com.example.bookstore.DTOs.order.OrderUpdateForAdmin;
import com.example.bookstore.DTOs.order.OrderUpdateForUserDto;
import com.example.bookstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/orders")
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/ordersList")
    public List<OrderRequestDto> getAll(){
        return orderService.getAll();
    }
    @GetMapping("/order/{orderID}")
    public Optional<OrderRequestDto> getByID(@PathVariable("orderID") long id){
        return orderService.getByID(id);}


    @PutMapping("/order")
    public void update(@RequestBody OrderUpdateForAdmin orderUpdateForAdmin) throws Exception {

        orderService.updateForAdmin(orderUpdateForAdmin);
    }
    @PostMapping("/order")
    public OrderCreateDto create(@RequestBody OrderCreateDto orderCreateDto) throws Exception {
        return orderService.create(orderCreateDto);
    }
    @DeleteMapping("/order/{orderID}")
    public void delete(@PathVariable("orderID") long id){
        orderService.deleteById(id);
    }

    }
