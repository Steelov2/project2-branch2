package com.example.bookstore.entities;

import com.example.bookstore.DTOs.order.OrderDto;
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
            generator = "genre_sequence"
    )
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    private User user;
    @OneToMany
    @JoinTable(
            name = "order_book",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> orderedBooks;
    private LocalDate createdAt;
    private Status status;

    public OrderDto convertOrderToDto(){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(this.getId());
        orderDto.setUser(this.getUser().convertUserToRequestDto());
        orderDto.setStatus(this.getStatus());
        orderDto.setOrderedBooks(this.getOrderedBooks()
                .stream()
                .map(Book::convertBookToResponseDto)
                .toList());
        orderDto.setCreatedAt(this.getCreatedAt());
        return orderDto;
    }

    public OrderDto convertOrderToUpdateDto(){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(this.getId());
        orderDto.setUser(this.getUser().convertUserToRequestDto());
        orderDto.setStatus(this.getStatus());
        orderDto.setOrderedBooks(this.getOrderedBooks()
                .stream()
                .map(Book::convertBookToResponseDto)
                .toList());
        orderDto.setCreatedAt(this.getCreatedAt());
        return orderDto;
    }


}
