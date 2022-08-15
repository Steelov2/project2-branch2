package com.example.bookstore.repository;

import com.example.bookstore.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository

public interface GenreRepo extends JpaRepository<Genre, Long> {
    List<Genre> findByGenreNameIsContainingIgnoreCase(String name);

    List<Genre> findAllByIdIn(List<Long> ids);

    @Query(value = """
                        SELECT *
                        FROM author a ,
                             author_books ab,
                             book_genre bg,
                             genre g
                        WHERE a.id = ab.author_id
                          and bg.genre_id= g.id
                          and ab.book_id= bg.book_id
                            and a.name = :authorName
            """, nativeQuery = true)
    Set<Genre> findAllByAuthor(String authorName);

    Boolean existsByGenreNameIn(List<String> genreName);

}

