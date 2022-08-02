package com.example.bookstore.DTOs.order;

import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.User.UserRequestDto;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Order;
import com.example.bookstore.entities.Status;
import com.example.bookstore.entities.User;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderUpdateDto {
    private Long id;
    private Long userId;
    private List<Long> orderedBooksIds;
    private Status status;

    public Order convertOrderDtoToEntity(List<Book> bbb, User uuu){
        Order order= new Order();
        order.setId(this.getId());
        order.setUser(uuu);
        order.setStatus(this.getStatus());
        order.setOrderedBooks(bbb);
        return order;
    }
}
