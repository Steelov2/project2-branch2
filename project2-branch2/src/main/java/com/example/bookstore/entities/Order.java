package com.example.bookstore.entities;

import com.example.bookstore.dto.order.OrderCreatDto;
import com.example.bookstore.dto.order.OrderRequestDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence")

    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToMany
    @JoinTable(
            name = "order_book",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> orderedBooks;
    private LocalDate createdAt;
    @Enumerated(EnumType.STRING)
    private Status status;

    public OrderRequestDto convertOrderToDto(){
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setId(this.getId());
        orderRequestDto.setUserId(this.getUser().getId());
        orderRequestDto.setStatus(this.getStatus());

        orderRequestDto.setOrderedBooks(this.getOrderedBooks()
                .stream()
                .map(Book::convertBookToBookRequestDto)
                .toList());
        orderRequestDto.setCreatedAt(this.getCreatedAt());
        return orderRequestDto;
    }

    public OrderCreatDto convertOrderToCrateDto(){
        OrderCreatDto orderCreatDto = new OrderCreatDto();
        orderCreatDto.setId(this.getId());
        orderCreatDto.setUserId(this.getUser().getId());
        orderCreatDto.setOrderedBooksIds(this.getOrderedBooks()
                .stream()
                .map(Book::getId)
                .toList());
        //orderCreateDto.setCreatedAt(this.getCreatedAt());
        return orderCreatDto;
    }


}
