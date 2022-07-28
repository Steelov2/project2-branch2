package com.example.bookstore.Repos;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher,Long> {
    List<Publisher> findByNameIsContainingIgnoreCase(String name);

}
