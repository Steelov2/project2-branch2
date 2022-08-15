package com.example.bookstore.dto.order;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Order;
import com.example.bookstore.entities.User;
import lombok.*;

import java.util.List;
@Data

public class OrderUpdateForUserDto {
    private Long id;
    private Long userId;
    private List<Long> orderedBookIds;

    public Order convertOrderUpdateForUserDtoToEntity(List<Book> bbb, User uuu) {
        Order order = new Order();

        order.setId(this.getId());

        order.setUser(uuu);

        order.setOrderedBooks(bbb);
        return order;
    }
}
