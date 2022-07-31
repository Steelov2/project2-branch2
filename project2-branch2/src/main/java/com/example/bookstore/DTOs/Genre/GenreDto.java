package com.example.bookstore.DTOs.Genre;

import com.example.bookstore.entities.Genre;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class GenreDto {
    private long id;
    private String name;

}
