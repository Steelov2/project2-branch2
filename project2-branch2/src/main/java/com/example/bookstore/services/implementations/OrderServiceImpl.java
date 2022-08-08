package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.order.OrderCreateDto;
import com.example.bookstore.DTOs.order.OrderRequestDto;
import com.example.bookstore.DTOs.order.OrderUpdateForAdmin;
import com.example.bookstore.DTOs.order.OrderUpdateForUserDto;
import com.example.bookstore.Repos.BookRepo;
import com.example.bookstore.Repos.OrderRepo;
import com.example.bookstore.Repos.UserRepo;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Order;
import com.example.bookstore.services.OrderService;
import lombok.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;

    private final BookRepo bookRepo;

    private final UserRepo userRepo;


    @Override
    public void create(OrderCreateDto orderCreateDto) throws Exception {
        int priceOfBooks = 0;
        val books = bookRepo.findAllByIdIn(orderCreateDto.getOrderedBooksIds());
        val user = userRepo.findById(orderCreateDto.getUserId()).orElseThrow();
        Order order = orderCreateDto.convertOrderCreateDtoToEntity(books, user);
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), user.getUsername())) {
            for (Book book : order.getOrderedBooks()) {
                priceOfBooks += book.getPrice();
            }
            if (priceOfBooks <= 10000 && !order.getUser().getIsBlocked())
                orderRepo.save(order).convertOrderToCrateDto();
            else if (priceOfBooks > 10000)
                throw new Exception("You have reached your purchase limit of 10000 ");
            else
                throw new Exception("The user is blocked");
        } else
            throw new Exception("You are not allowed to create order");
    }



    @SneakyThrows
    @Override
    public void updateForUser(OrderUpdateForUserDto orderUpdateForUserDto) {
        int priceOfBooks = 0;
        val books = bookRepo.findAllByIdIn(orderUpdateForUserDto.getOrderedBookIds());
        val user = userRepo.findById(orderUpdateForUserDto.getUserId()).orElseThrow();
        Order order = orderUpdateForUserDto.convertOrderUpdateForUserDtoToEntity(books, user);
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), user.getUsername())) {
            if (!order.getUser().getIsBlocked()) {
                for (Book book : order.getOrderedBooks()) {
                    priceOfBooks += book.getPrice();
                }
                if (priceOfBooks <= 10000) {
                    Order existingOrder;
                    existingOrder = orderRepo.findById(order.getId()).orElseThrow();

                    existingOrder.setOrderedBooks(order.getOrderedBooks());
                    existingOrder.setUser(order.getUser());
                    existingOrder.setId(order.getId());

                    orderRepo.save(order);
                } else
                    throw new Exception("You have reached your purchase limit of 10000 ");
            } else throw new Exception("The user " + user.getUsername() + "is blocked");
        } else throw new Exception("You are not allowed to update this order");
    }

    @SneakyThrows
    @Override
    public void updateForAdmin(OrderUpdateForAdmin orderUpdateForAdmin) {
        int priceOfBooks = 0;
        val user = userRepo.findById(orderUpdateForAdmin.getUserId()).orElseThrow();
        Order order = orderUpdateForAdmin.convertOrderUpdateForAdminToEntity(user);

        if (!order.getUser().getIsBlocked()) {
            for (Book book : order.getOrderedBooks()) {
                priceOfBooks += book.getPrice();
            }
            if (priceOfBooks <= 10000) {
                Order existingOrder;
                existingOrder = orderRepo.findById(order.getId()).orElseThrow();

                existingOrder.setStatus(order.getStatus());
                existingOrder.setUser(order.getUser());
                existingOrder.setId(order.getId());

                if (order.getCreatedAt() != null) existingOrder.setCreatedAt(order.getCreatedAt());

                orderRepo.save(order);
            } else throw new Exception("You have reached the purchase limit of 10000 ");
        } else throw new Exception("The user " + user.getUsername() + "is blocked");
    }


    @Override
    public void deleteById(Long id) {
        orderRepo.deleteById(id);
    }

    @Override
    public List<OrderRequestDto> getAll() {
        return orderRepo.findAll()
                .stream()
                .map(Order::convertOrderToDto)
                .toList();
    }

    @Override
    public Optional<OrderRequestDto> getByID(Long id) {
        return orderRepo.findById(id)
                .map(Order::convertOrderToDto);
    }
}
