package com.example.bookstore.DTOs.Genre;

import com.example.bookstore.entities.Genre;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class GenreRequestDto {
    private Long id;
    private String name;

    public Genre convertGenreRequestDtoToEntity() {
        Genre genre =new Genre();
        genre.setName(this.getName());
        genre.setId(this.getId());
        return genre;
    }
}
