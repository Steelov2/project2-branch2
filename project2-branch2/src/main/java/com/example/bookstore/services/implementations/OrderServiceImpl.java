package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.order.OrderCreateDto;
import com.example.bookstore.DTOs.order.OrderRequestDto;
import com.example.bookstore.DTOs.order.OrderUpdateDto;
import com.example.bookstore.Repos.BookRepo;
import com.example.bookstore.Repos.OrderRepo;
import com.example.bookstore.Repos.UserRepo;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Order;
import com.example.bookstore.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.val;
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
    public OrderCreateDto create(OrderCreateDto orderCreateDto) throws Exception {
        int priceOfBooks = 0;
        val books = bookRepo.findAllByIdIn(orderCreateDto.getOrderedBooksIds());
        val user = userRepo.findById(orderCreateDto.getUserId()).orElseThrow();
        Order order = orderCreateDto.convertOrderCreateDtoToEntity(books ,user );
        for (Book book : order.getOrderedBooks()) {
            priceOfBooks += book.getPrice();
        }
        if (priceOfBooks <= 10000 && !order.getUser().getIsBlocked())
            return orderRepo.save(order).convertOrderToCrateDto();
        else if (priceOfBooks > 10000)
            throw new Exception("You have reached your purchase limit of 10000 ");
        else
            throw new Exception("The user is blocked");
    }


    @Override
    public void update(OrderUpdateDto orderUpdateDto) throws Exception {
        int priceOfBooks = 0;
        val books = bookRepo.findAllByIdIn(orderUpdateDto.getOrderedBooksIds());
        val user = userRepo.findById(orderUpdateDto.getUserId()).orElseThrow();
        Order order = orderUpdateDto.convertOrderDtoToEntity(books, user);

        if (!order.getUser().getIsBlocked()) {
            for (Book book : order.getOrderedBooks()) {
                priceOfBooks += book.getPrice();
            }
            if (priceOfBooks <= 10000) {
                Order existingOrder;
                existingOrder = orderRepo.findById(order.getId()).orElseThrow();
                existingOrder.setOrderedBooks(order.getOrderedBooks());
                existingOrder.setStatus(order.getStatus());
                existingOrder.setUser(order.getUser());

                existingOrder.setId(order.getId());
                orderRepo.save(order);
            } else
                throw new Exception("You have reached your purchase limit of 10000 ");
        } else throw new Exception("The user " + user.getUsername() + "is blocked");
    }



        @Override
        public void deleteById (Long id){
            orderRepo.deleteById(id);
        }

        @Override
        public List<OrderRequestDto> getAll () {
            return orderRepo.findAll()
                    .stream()
                    .map(Order::convertOrderToDto)
                    .toList();
        }

        @Override
        public Optional<OrderRequestDto> getByID (Long id){
            return orderRepo.findById(id)
                    .map(Order::convertOrderToDto);
        }
    }
