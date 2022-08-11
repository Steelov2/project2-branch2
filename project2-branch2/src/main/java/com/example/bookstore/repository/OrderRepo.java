package com.example.bookstore.repository;

import com.example.bookstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepo extends JpaRepository<Order, Long> {

}


