package com.example.bookstore.services.implementations;

import com.example.bookstore.dto.order.OrderCreatDto;
import com.example.bookstore.dto.order.OrderRequestDto;
import com.example.bookstore.dto.order.OrderUpdateForAdmin;
import com.example.bookstore.dto.order.OrderUpdateForUserDto;
import com.example.bookstore.entities.Status;
import com.example.bookstore.entities.User;
import com.example.bookstore.exceptions.LimitedRightsException;
import com.example.bookstore.exceptions.ResourceNotFoundException;
import com.example.bookstore.repository.BookRepo;
import com.example.bookstore.repository.OrderRepo;
import com.example.bookstore.repository.UserRepo;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Order;
import com.example.bookstore.services.OrderService;
import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;

    private final BookRepo bookRepo;

    private final UserRepo userRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, BookRepo bookRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void create(OrderCreatDto orderCreatDto) throws Exception {
        int priceOfBooks = 0;
        List<Book> books = bookRepo.findAllByIdIn(orderCreatDto.getOrderedBooksIds());
        User user = userRepo.findById(orderCreatDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("There is no such user"));
        Order order = orderCreatDto.convertOrderCreateDtoToEntity(books, user);
        if (books.size() != orderCreatDto.getOrderedBooksIds().size())
            throw new ResourceNotFoundException("There is no such book with ID");
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), user.getUsername())) {
            for (Book book : order.getOrderedBooks()) {
                priceOfBooks += book.getPrice();
                if (!book.getIsInStock())
                    throw new ResourceNotFoundException("No such book in stock");
            }
            if (priceOfBooks <= 10000)
                orderRepo.save(order).convertOrderToCrateDto();
            else throw new LimitedRightsException("You have reached your purchase limit of 10000 ");
        } else throw new LimitedRightsException("You are not allowed to create order");


    }


    @SneakyThrows
    @Override
    public void updateForUser(OrderUpdateForUserDto orderUpdateForUserDto) {
        int priceOfBooks = 0;
        List<Book> books = bookRepo.findAllByIdIn(orderUpdateForUserDto.getOrderedBookIds());
        User user = userRepo.findById(orderUpdateForUserDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no such user"));
        Order order = orderUpdateForUserDto.convertOrderUpdateForUserDtoToEntity(books, user);
        //он сохраняет новые данные
        Order order1 = new Order();
        //он вытаскивает из репы
        Order existingOrder = orderRepo.findById(order.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("There is no order with ID: %d", order.getId())));
        if (books.size() != orderUpdateForUserDto.getOrderedBookIds().size())
            throw new ResourceNotFoundException("There is no such book with ID");
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), user.getUsername())) {
            if (existingOrder.getStatus() == Status.CANCELLED)
                throw new LimitedRightsException("This order is cancelled, you cannot update it");
            else {
                for (Book book : order.getOrderedBooks()) {
                    priceOfBooks += book.getPrice();
                    if (!book.getIsInStock())
                        throw new ResourceNotFoundException("No such book in stock");
                }
                if (priceOfBooks <= 10000) {

                    order1.setOrderedBooks(order.getOrderedBooks());

                    order1.setUser(existingOrder.getUser());
                    order1.setId(existingOrder.getId());
                    order1.setStatus(existingOrder.getStatus());
                    order1.setCreatedAt(existingOrder.getCreatedAt());

                    orderRepo.save(order1);

                } else throw new LimitedRightsException("You have reached your purchase limit of 10000 ");
            }
        } else throw new LimitedRightsException("You are not allowed to update this order, bcs it is not yours");
    }

    @Override
    public void updateForAdmin(OrderUpdateForAdmin orderUpdateForAdmin) {
        User user = userRepo.findById(orderUpdateForAdmin.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("No such user"));
        //получает то что я написал в постмане
        Order order = orderUpdateForAdmin.convertOrderUpdateForAdminToEntity(user);
        //он сохраняет новые данные
        Order order1 = new Order();
        //он вытаскивает из репы
        Order existingOrder = orderRepo.findById(order.getId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no such order"));
        if (existingOrder.getStatus() == Status.CANCELLED)
            throw new LimitedRightsException("This order is cancelled, you cannot update it");
        else if(user.getIsBlocked())
            throw new LimitedRightsException("This user is blocked, you cannot the order it");
        else {
            order1.setStatus(order.getStatus());
            order1.setOrderedBooks(existingOrder.getOrderedBooks());
            order1.setUser(existingOrder.getUser());
            order1.setId(existingOrder.getId());
            order1.setCreatedAt(existingOrder.getCreatedAt());
            orderRepo.save(order1);
        }


    }


    @Override
    public void deleteById(Long id) {
        Order existingOrder = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("The order with ID: %d is not found or already deleted", id)));

        if (existingOrder.getStatus()==Status.CANCELLED)
            orderRepo.deleteById(id);
        else
            throw new LimitedRightsException(String.format("You cannot delete the order with ID: %d, until it will not be cancelled ", id));

    }

    @Override
    public List<OrderRequestDto> getAll() {
        return orderRepo.findAll().stream().map(Order::convertOrderToDto).toList();
    }

    @Override
    public Optional<OrderRequestDto> getByID(Long id) {
        if (orderRepo.existsById(id)) return orderRepo.findById(id).map(Order::convertOrderToDto);
        else
            throw new ResourceNotFoundException(String.format("The order with ID: %d is not found or doesn't exist", id));
    }

}
