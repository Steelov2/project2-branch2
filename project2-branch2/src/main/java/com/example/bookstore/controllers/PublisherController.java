package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.Publisher.PublisherDTO;
import com.example.bookstore.DTOs.Publisher.PublisherGetDto;
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
    public List<PublisherDTO> getAll(){
        return  publisherService.getAll();
    }
    @GetMapping("/publisher/{publisherID}")
    private Optional<PublisherDTO> getPublisherById(@PathVariable("publisherID") Long id)
    {
        return publisherService.getByID(id);
    }
    @GetMapping("/publisher/bookName/{bookName}")
    private List<PublisherDTO> getAuthorByName(@PathVariable("bookName") String name){
        return publisherService.getByNameContaining(name);
    }
    @DeleteMapping("/publisher/{publisherID}")
    private void deletePublisherById(@PathVariable("publisherID") Long id)
    {
        publisherService.deleteByID(id);
    }
    @PostMapping("/publisher")
    private PublisherGetDto savePublisher(@RequestBody PublisherGetDto publisherGetDTO)
    {
        return  publisherService.create(publisherGetDTO);
    }
    @PutMapping("/publisher/{publisherID}")
    private void updatePublisher(@RequestBody PublisherDTO publisherDTO,@PathVariable("publisherID") Long id)    {
        if(!Objects.equals(id, publisherDTO.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        publisherService.update(publisherDTO,id);
    }







}
