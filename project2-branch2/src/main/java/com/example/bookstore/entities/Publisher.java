package com.example.bookstore.entities;

import com.example.bookstore.DTOs.Publisher.PublisherRequestDto;

import javax.persistence.*;
import java.util.List;

import com.example.bookstore.DTOs.Publisher.PublisherResponseDto;
import lombok.*;

@Entity
@Table(name = "publisher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Publisher {
    @Id
    @SequenceGenerator(
            name = "publisher_sequence",
            sequenceName = "publisher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "publisher_sequence"
    )
    private Long id;
    private String name;
    @OneToMany(mappedBy = "publisher")
    private List<Book> publishedBooksList;



}
