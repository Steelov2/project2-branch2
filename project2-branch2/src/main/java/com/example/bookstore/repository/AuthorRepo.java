package com.example.bookstore.repository;

import com.example.bookstore.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Long> {
    List<Author> findByNameIsContainingIgnoreCaseOrSurnameIsContainingIgnoreCaseOrPatronymicIsContainingIgnoreCase(String name, String surname, String patronymic );
    @Query(value="""
            SELECT *
            FROM author a ,
                 author_books ab,
                 book_genre bg,
                 genre g
            WHERE a.id = ab.author_id
              and bg.genre_id= g.id
              and ab.book_id= bg.book_id
              and g.name = :genreName
""",nativeQuery = true)
    Set<Author> findAllByGenre(String genreName);




    Boolean existsBySurnameIsContainingIgnoreCase(String surname);
    Boolean existsByNameIsContainingIgnoreCase(String name);
    Boolean existsByPatronymicIsContainingIgnoreCase(String patronymic);

    List<Author> findAllByIdIn(List<Long> ids);





}
//@Param("name")