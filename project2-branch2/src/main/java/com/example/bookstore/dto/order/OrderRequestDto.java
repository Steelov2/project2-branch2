package com.example.bookstore.dto.order;

import com.example.bookstore.dto.book.BookResponseDto;
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
public class OrderRequestDto {
    private Long id;
    private Long userId;
    private List<BookResponseDto> orderedBooks;
    private Status status;
    private LocalDate createdAt = LocalDate.now();


    public Order convertOrderRequestDtoToEntity(User uuu) {
        Order order = new Order();
        order.setId(this.getId());
        order.setUser(uuu);
        order.setStatus(this.getStatus());
        order.setOrderedBooks(this.
                getOrderedBooks()
                .stream()
                .map(BookResponseDto::convertBookRequestDtoDtoToEntity)
                .toList());
        order.setCreatedAt(this.getCreatedAt());
        return order;
    }
}
