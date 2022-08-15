package com.example.bookstore.entities;

import com.example.bookstore.dto.genre.GenreRequestDto;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "GENRE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @SequenceGenerator(
            name = "genre_sequence",
            sequenceName = "genre_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "genre_sequence"
    )
    private Long id;

    @Column(name = "name")
    private String genreName;

    public GenreRequestDto convertGenreRequestToDto() {
        GenreRequestDto genreRequestDto = new GenreRequestDto();
        genreRequestDto.setName(this.getGenreName());
        genreRequestDto.setId(this.getId());
        return genreRequestDto;
    }


}
