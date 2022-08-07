package com.example.bookstore.controllers.controller1;

import com.example.bookstore.DTOs.Publisher.PublisherRequestDto;
import com.example.bookstore.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public class PublisherUserController {
    private final PublisherService publisherService;
    @Autowired
    public PublisherUserController(PublisherService publisherService){
        this.publisherService= publisherService;
    }

    @GetMapping("/publishersList")
    public List<PublisherRequestDto> getAll(){
        return  publisherService.getAll();
    }
    @GetMapping("/publisher/{publisherID}")
    public Optional<PublisherRequestDto> getPublisherById(@PathVariable("publisherID") Long id)
    {
        return publisherService.getByID(id);
    }
    @GetMapping("/publisher/bookName/{bookName}")
    public List<PublisherRequestDto> getAuthorByName(@PathVariable("bookName") String name){
        return publisherService.getByNameContaining(name);
    }
}
