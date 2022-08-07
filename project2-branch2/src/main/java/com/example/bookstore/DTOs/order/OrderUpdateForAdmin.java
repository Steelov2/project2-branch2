package com.example.bookstore.DTOs.order;

import com.example.bookstore.DTOs.Book.BookResponseDto;
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
public class OrderUpdateForAdmin {
    private Long id;
    private Long userId;
    private List<Long> orderedBookIds;
    private Status status;

    public Order convertOrderUpdateForAdminToEntity(List<Book> bbb, User uuu) {
        Order order = new Order();

        order.setId(this.getId());
        order.setStatus(this.getStatus());

        order.setUser(uuu);
        order.setOrderedBooks(bbb);
        return order;
    }
}
