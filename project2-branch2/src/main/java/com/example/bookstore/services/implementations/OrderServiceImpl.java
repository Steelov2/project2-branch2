package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.order.OrderDto;
import com.example.bookstore.DTOs.order.OrderUpdateDto;
import com.example.bookstore.Repos.BookRepo;
import com.example.bookstore.Repos.OrderRepo;
import com.example.bookstore.Repos.UserRepo;
import com.example.bookstore.entities.Genre;
import com.example.bookstore.entities.Order;
import com.example.bookstore.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    private final BookRepo bookRepo;

    private final UserRepo userRepo;



    @Override
    public OrderDto create(OrderDto orderDTO) {
        Order order = orderDTO.convertOrderDtoToEntity();
        Order orderCreated = orderRepo.save(order);
        return orderCreated.convertOrderToDto();
    }

    @Override
    public void update(OrderUpdateDto orderUpdateDto) {
        val books =bookRepo.findAllByIdIn(orderUpdateDto.getOrderedBooksIds());
        val user =userRepo.findById(orderUpdateDto.getUserId()).orElseThrow();
        Order order=orderUpdateDto.convertOrderDtoToEntity(books, user);


        Order existingOrder;

            existingOrder = orderRepo.findById(order.getId()).orElseThrow();
            existingOrder.setCreatedAt(order.getCreatedAt());
            existingOrder.setOrderedBooks(order.getOrderedBooks());
            existingOrder.setStatus(order.getStatus());
            existingOrder.setUser(order.getUser());
            existingOrder.setId(order.getId());
            orderRepo.save(order);


    }

    @Override
    public void deleteById(Long id) {
        orderRepo.deleteById(id);
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepo.findAll()
                .stream()
                .map(Order::convertOrderToDto)
                .toList();
    }

    @Override
    public Optional<OrderDto> getByID(Long id) {
        return orderRepo.findById(id)
                .map(Order::convertOrderToDto);
    }
}
