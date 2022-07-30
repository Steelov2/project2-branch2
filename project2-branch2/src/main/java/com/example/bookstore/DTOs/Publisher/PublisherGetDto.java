package com.example.bookstore.DTOs.Publisher;

import com.example.bookstore.entities.Publisher;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PublisherGetDto {
    private long id;

    private String name;

    public PublisherGetDto(String name) {
        this.name = name;
    }

    public Publisher convertPublisherGetDtoToEntity( ) {
        Publisher publisher = new Publisher();
        publisher.setName(this.getName());
        publisher.setId(this.getId());
        return publisher;
    }
}
