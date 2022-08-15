package com.example.bookstore.repository;

import com.example.bookstore.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    @Query(value = """
                        select *
                        from author a
                        where (a.name = :name AND a.surname = :surname AND a.patronymic = :patronymic)
                           OR (a.name = :name AND a.surname = :surname)
                           OR (a.name = :name AND a.surname = :patronymic)
                           OR (a.name = :surname AND a.surname = :patronymic)
                           OR a.name = :name
                           OR a.surname = :surname
                           OR a.patronymic = :patronymic
            """, nativeQuery = true)
    List<Author> findByFullName(String surname, String name, String patronymic);

    @Query(value = """
                        SELECT *
                        FROM author a ,
                             author_books ab,
                             book_genre bg,
                             genre g
                        WHERE a.id = ab.author_id
                          and bg.genre_id= g.id
                          and ab.book_id= bg.book_id
                          and g.name in :genreName
            """, nativeQuery = true)
    List<Author> findAllByGenre(List<String> genreName);


    Boolean existsBySurnameIsContainingIgnoreCase(String surname);

    Boolean existsByNameIsContainingIgnoreCase(String name);

    Boolean existsByPatronymicIsContainingIgnoreCase(String patronymic);

    List<Author> findAllByIdIn(List<Long> ids);


}
//@Param("name")