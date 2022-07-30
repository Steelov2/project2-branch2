package com.example.bookstore.entities;

import com.example.bookstore.DTOs.Publisher.PublisherDTO;

import javax.persistence.*;
import java.util.List;

import com.example.bookstore.DTOs.Publisher.PublisherGetDto;
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


    public PublisherDTO convertPublisherToDto() {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setName(this.getName());
        publisherDTO.setId(this.getId());
        publisherDTO.setPublishedBooks(this.getPublishedBooksList().stream().map(Book::convertBookToBookGetDto).toList());
        return publisherDTO;
    }

    public PublisherGetDto convertPublisherToGetDto() {
        PublisherGetDto publisherGetDto = new PublisherGetDto();
        publisherGetDto.setName(this.getName());
        publisherGetDto.setId(this.getId());
        return publisherGetDto;
    }
}
