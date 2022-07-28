package com.example.bookstore.Repos;

import com.example.bookstore.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface GenreRepo extends JpaRepository<Genre,Long> {
    List<Genre> findByNameIsContainingIgnoreCase(String name);

}

