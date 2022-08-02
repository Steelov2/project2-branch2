package com.example.bookstore.DTOs.order;

import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.User.UserRequestDto;
import com.example.bookstore.entities.Order;
import com.example.bookstore.entities.Status;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {
    private Long id;
    private UserRequestDto user;
    private List<BookResponseDto> orderedBooks;
    private Status status;
    private LocalDate createdAt;


    public OrderDto(UserRequestDto user,
                    List<BookResponseDto> orderedBooks,
                    Status status,
                    LocalDate createdAt) {
        this.user = user;
        this.orderedBooks = orderedBooks;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Order convertOrderDtoToEntity(){
        Order order= new Order();
        order.setId(this.getId());
        order.setUser(this.getUser().convertUserRequestDtoToEntity());
        order.setStatus(this.getStatus());
        order.setOrderedBooks(this.getOrderedBooks().stream().map(BookResponseDto::convertBookRequestDtoDtoToEntity).toList());
        order.setCreatedAt(this.getCreatedAt());
        return order;
    }
}
