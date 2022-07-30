package com.example.bookstore.DTOs.order;

import com.example.bookstore.DTOs.Book.BookGetDto;
import com.example.bookstore.DTOs.User.UserDto;
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
    private UserDto user;
    private List<BookGetDto> orderedBooks;
    private Status status;
    private LocalDate createdAt;


    public OrderDto(UserDto user,
                    List<BookGetDto> orderedBooks,
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
        order.setUser(this.getUser().convertUserDtoToEntity());
        order.setStatus(this.getStatus());
        order.setOrderedBooks(this.getOrderedBooks()
                .stream()
                .map(BookGetDto::convertGetDtoToEntity)
                .toList());
        order.setCreatedAt(this.getCreatedAt());
        return order;
    }
}
