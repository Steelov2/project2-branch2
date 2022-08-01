package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.order.OrderDto;
import com.example.bookstore.Repos.OrderRepo;
import com.example.bookstore.entities.Genre;
import com.example.bookstore.entities.Order;
import com.example.bookstore.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    @Override
    public OrderDto create(OrderDto orderDTO) {
        Order order = orderDTO.convertOrderDtoToEntity();
        Order orderCreated = orderRepo.save(order);
        return orderCreated.convertOrderToDto();
    }

    @Override
    public void update(OrderDto orderDTO) {
        Order order=orderDTO.convertOrderDtoToEntity();
        Order existingOrder;
        try {
            existingOrder = orderRepo.findById(order.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingOrder.setCreatedAt(order.getCreatedAt());
            existingOrder.setOrderedBooks(order.getOrderedBooks());
            existingOrder.setStatus(order.getStatus());
            existingOrder.setUser(order.getUser());
            existingOrder.setId(order.getId());
            orderRepo.save(order);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }

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
