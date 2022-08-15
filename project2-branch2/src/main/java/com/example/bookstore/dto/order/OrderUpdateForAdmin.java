package com.example.bookstore.dto.order;

import com.example.bookstore.entities.Order;
import com.example.bookstore.entities.Status;
import com.example.bookstore.entities.User;
import lombok.*;

@Data
public class OrderUpdateForAdmin {
    private Long id;
    private Long userId;

    private Status status;

    public Order convertOrderUpdateForAdminToEntity( User uuu) {
        Order order = new Order();

        order.setId(this.getId());
        order.setStatus(this.getStatus());

        order.setUser(uuu);
        return order;
    }
}
