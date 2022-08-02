package com.example.bookstore.Repos;

import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Long> {
    List<Author> findByName(String name);
    @Query(value="""
            SELECT *
            FROM author b,
                 author_genre bg,
                 genre g
            WHERE b.id = bg.author_id
              and bg.genre_id = g.id
              and g.name = :genreName
""",nativeQuery = true)
    List<Author> findAllByGenre(String genreName);

    List<Author> findAllByIdIn(List<Long> ids);





}
//@Param("name")