package com.example.bookstore.services.implementations;

import com.example.bookstore.dto.order.OrderCreateDto;
import com.example.bookstore.dto.order.OrderRequestDto;
import com.example.bookstore.dto.order.OrderUpdateForAdmin;
import com.example.bookstore.dto.order.OrderUpdateForUserDto;
import com.example.bookstore.entities.User;
import com.example.bookstore.exceptions.LimitedRightsException;
import com.example.bookstore.exceptions.ResourceNotFoundException;
import com.example.bookstore.exceptions.UserIsBlockedException;
import com.example.bookstore.repository.BookRepo;
import com.example.bookstore.repository.OrderRepo;
import com.example.bookstore.repository.UserRepo;
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
        List<Book> books = bookRepo.findAllByIdIn(orderCreateDto.getOrderedBooksIds());
        User user = userRepo.findById(orderCreateDto.getUserId()).orElseThrow();
        Order order = orderCreateDto.convertOrderCreateDtoToEntity(books, user);
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), user.getUsername())) {
            for (Book book : order.getOrderedBooks()) {
                priceOfBooks += book.getPrice();
            }
            if (priceOfBooks <= 10000 && !order.getUser().getIsBlocked())
                orderRepo.save(order).convertOrderToCrateDto();
            else if (priceOfBooks > 10000)
                throw
                        new LimitedRightsException("You have reached your purchase limit of 10000 ");
            else
                throw
                        new UserIsBlockedException(String.format("The user %s is blocked",user.getUsername()));
        } else
            throw
                    new LimitedRightsException("You are not allowed to create order");
    }


    @SneakyThrows
    @Override
    public void updateForUser(OrderUpdateForUserDto orderUpdateForUserDto) {
        int priceOfBooks = 0;
        List<Book> books = bookRepo.findAllByIdIn(orderUpdateForUserDto.getOrderedBookIds());
        User user = userRepo.findById(orderUpdateForUserDto.getUserId()).orElseThrow();
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
                } else throw
                        new LimitedRightsException("You have reached your purchase limit of 10000 ");
            } else throw
                    new UserIsBlockedException("The user " + user.getUsername() + "is blocked");
        } else throw
                new LimitedRightsException("You are not allowed to update this order");
    }

    @SneakyThrows
    @Override
    public void updateForAdmin(OrderUpdateForAdmin orderUpdateForAdmin) {
        int priceOfBooks = 0;
        User user = userRepo.findById(orderUpdateForAdmin.getUserId()).orElseThrow();
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
            } else throw
                    new LimitedRightsException("You have reached the purchase limit of 10000 ");

        } else throw
                new UserIsBlockedException("The user " + user.getUsername() + "is blocked");
    }


    @Override
    public void deleteById(Long id) {
        if (orderRepo.existsById(id))
            orderRepo.deleteById(id);
        else
            throw
                    new ResourceNotFoundException(String.format("The genre with ID: %d is not found or already deleted", id));
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
        if (orderRepo.existsById(id))
            return orderRepo.findById(id)
                    .map(Order::convertOrderToDto);
        else throw
                new ResourceNotFoundException(String.format("The genre with ID: %d is not found or doesn't exist", id));
    }
}
