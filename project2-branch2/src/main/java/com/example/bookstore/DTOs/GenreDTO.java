package com.example.bookstore.DTOs;

import com.example.bookstore.entities.Genre;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class GenreDTO {
    private long id;
    private String name;
    public Genre convertGenreDtoToEntity( ) {
        Genre genre = new Genre();
        genre.setName(this.getName());
        genre.setId(this.getId());
        return genre;
    }
}
