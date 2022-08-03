package com.example.bookstore.DTOs.order;

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
public class OrderCreateDto {
    private Long id;
    private Long userId;
    private List<Long> orderedBooksIds;
    private Status status;
    private LocalDate createdAt = LocalDate.now();


    public Order convertOrderCreateDtoToEntity(List<Book> bbb, User uuu) {
        Order order = new Order();
        order.setId(this.getId());
        order.setStatus(this.getStatus());
        order.setCreatedAt(this.getCreatedAt());
        order.setUser(uuu);
        order.setOrderedBooks(bbb);
        return order;
    }
}
