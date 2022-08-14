package com.example.bookstore.dto.order;

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
public class OrderCreatDto {
    private Long id;
    private Long userId;
    private List<Long> orderedBooksIds;


    public Order convertOrderCreateDtoToEntity(List<Book> bbb, User uuu) {
        Order order = new Order();
        order.setId(this.getId());
        order.setCreatedAt(LocalDate.now());
        order.setStatus(Status.CREATED);

        order.setUser(uuu);
        order.setOrderedBooks(bbb);
        return order;
    }
}

