package com.example.bookstore.repository;

import com.example.bookstore.entities.Genre;
import com.example.bookstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepo extends JpaRepository<Order, Long> {


}


