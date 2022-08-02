package com.example.bookstore.controllers;
import com.example.bookstore.DTOs.order.OrderDto;
import com.example.bookstore.DTOs.order.OrderUpdateDto;
import com.example.bookstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/order")
    private OrderDto create(@RequestBody OrderDto orderDTO){
        return orderService.create(orderDTO);
    }
    @PutMapping("/order")
    private void update(@RequestBody OrderUpdateDto orderUpdateDto){

        orderService.update(orderUpdateDto);
    }
    @DeleteMapping("/order/{orderID}")
    private void delete(@PathVariable("orderID") long id){
        orderService.deleteById(id);
    }
    @GetMapping("/order")
    private List<OrderDto> getAll(){
        return orderService.getAll();
    }
    @GetMapping("/order/{orderID}")
    private Optional<OrderDto> getByID(@PathVariable("orderID") long id){
        return orderService.getByID(id);
    }
}