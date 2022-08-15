package com.example.bookstore.entities;

import javax.persistence.*;
import java.util.List;

import com.example.bookstore.dto.publisher.PublisherResponseDto;
import com.example.bookstore.dto.publisher.PublisherRequestDto;
import lombok.*;

@Entity
@Table(name = "publisher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public PublisherRequestDto convertPublisherToRequestDto() {
        PublisherRequestDto publisherRequestDTO = new PublisherRequestDto();
        publisherRequestDTO.setName(this.getName());
        publisherRequestDTO.setId(this.getId());
        publisherRequestDTO.setPublishedBooks(this.getPublishedBooksList().stream().map(Book::convertBookToDto).toList());
        return publisherRequestDTO;
    }

    public PublisherResponseDto convertPublisherToResponseDto() {
        PublisherResponseDto publisherResponseDto = new PublisherResponseDto();
        publisherResponseDto.setName(this.getName());
        publisherResponseDto.setId(this.getId());
        return publisherResponseDto;
    }


}
