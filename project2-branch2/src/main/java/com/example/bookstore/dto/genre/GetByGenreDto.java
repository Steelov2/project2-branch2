package com.example.bookstore.dto.genre;

import com.example.bookstore.entities.Genre;
import lombok.Data;

import java.util.List;
@Data
public class GetByGenreDto {
    List<Long> genreList;
    public Genre convertGetByGenreDtoToEntity() {
        Genre genre =new Genre();
        genre.setGenreName(this.genreList.toString());
        return genre;
    }
}
