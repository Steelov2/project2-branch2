package com.example.bookstore.controllers;
import com.example.bookstore.DTOs.order.OrderCreateDto;
import com.example.bookstore.DTOs.order.OrderRequestDto;
import com.example.bookstore.DTOs.order.OrderUpdateDto;
import com.example.bookstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/ordersList")
    private List<OrderRequestDto> getAll(){
        return orderService.getAll();
    }
    @GetMapping("/order/{orderID}")
    private Optional<OrderRequestDto> getByID(@PathVariable("orderID") long id){
        return orderService.getByID(id);}


    @PutMapping("/order")
    private void update(@RequestBody OrderUpdateDto orderUpdateDto) throws Exception {

        orderService.update(orderUpdateDto);
    }
    @PostMapping("/order")
    private OrderCreateDto create(@RequestBody OrderCreateDto orderCreateDto) throws Exception {
        return orderService.create(orderCreateDto);
    }
    @DeleteMapping("/order/{orderID}")
    private void delete(@PathVariable("orderID") long id){
        orderService.deleteById(id);
    }

    }
