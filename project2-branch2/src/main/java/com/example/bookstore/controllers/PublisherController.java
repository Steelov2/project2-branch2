package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.Publisher.PublisherRequestDto;
import com.example.bookstore.DTOs.Publisher.PublisherResponseDto;
import com.example.bookstore.DTOs.Publisher.PublisherUpdateDto;
import com.example.bookstore.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping()
public class PublisherController {
    private final PublisherService publisherService;
    @Autowired
    public PublisherController(PublisherService publisherService){
        this.publisherService= publisherService;
    }

    @GetMapping("/publisher")
    public List<PublisherRequestDto> getAll(){
        return  publisherService.getAll();
    }
    @GetMapping("/publisher/{publisherID}")
    private Optional<PublisherRequestDto> getPublisherById(@PathVariable("publisherID") Long id)
    {
        return publisherService.getByID(id);
    }
    @GetMapping("/publisher/bookName/{bookName}")
    private List<PublisherRequestDto> getAuthorByName(@PathVariable("bookName") String name){
        return publisherService.getByNameContaining(name);
    }
    @DeleteMapping("/publisher/{publisherID}")
    private void deletePublisherById(@PathVariable("publisherID") Long id)
    {
        publisherService.deleteByID(id);
    }
    @PostMapping("/publisher")
    private PublisherResponseDto savePublisher(@RequestBody PublisherResponseDto publisherResponseDTO)
    {
        return  publisherService.create(publisherResponseDTO);
    }
    @PutMapping("/publisher")
    private void updatePublisher(@RequestBody PublisherUpdateDto publisherUpdateDto)    {

        publisherService.update(publisherUpdateDto);
    }







}
