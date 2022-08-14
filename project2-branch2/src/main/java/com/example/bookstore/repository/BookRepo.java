package com.example.bookstore.repository;
import com.example.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
    List<Book> findByNameIsContainingIgnoreCase(String name);
    @Query(value="""
            SELECT b.*
            FROM book b,
                 book_genre bg,
                 genre g
            WHERE b.id = bg.book_id
              and bg.genre_id = g.id
              and g.name  in :genreName
""",nativeQuery = true)
    List<Book> findAllByGenre(List<String> genreName);

    Boolean existsByNameIsContainingIgnoreCase(String name);
    List<Book> findAllByIdIn(List<Long> ids);



}
