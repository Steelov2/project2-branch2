package com.example.bookstore.entities;

import com.example.bookstore.DTOs.GenreDTO;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "GENRE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Genre {
    @Id
    @SequenceGenerator(
            name="genre_sequence",
            sequenceName="genre_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "genre_sequence"
    )
    private Long id;
    @Column(name = "name")
    private String name;



    public GenreDTO convertGenreToDto() {
        GenreDTO genreDTO =new GenreDTO();
        genreDTO.setName(this.getName());
        genreDTO.setId(this.getId());

        return genreDTO;
    }
}
