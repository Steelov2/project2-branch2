package com.example.bookstore.DTOs.order;

import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.User.UserResponseDto;
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
    private long id;
    private UserResponseDto user;
    private List<BookResponseDto> orderedBooks;
    private Status status;
    private LocalDate createdAt;


    public OrderDto(UserResponseDto user,
                    List<BookResponseDto> orderedBooks,
                    Status status,
                    LocalDate createdAt) {
        this.user = user;
        this.orderedBooks = orderedBooks;
        this.status = status;
        this.createdAt = createdAt;
    }


}
