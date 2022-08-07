package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.Publisher.PublisherRequestDto;
import com.example.bookstore.DTOs.Publisher.PublisherResponseDto;
import com.example.bookstore.DTOs.Publisher.PublisherUpdateDto;
import com.example.bookstore.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/publishers")
public class PublisherAdminController {
    private final PublisherService publisherService;
    @Autowired
    public PublisherAdminController(PublisherService publisherService){
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
    @GetMapping("/publisher/publisherName/{publisherName}")
    public List<PublisherRequestDto> getPublisherById(@PathVariable("publisherName") String name)
    {
        return publisherService.getByNameContaining(name);
    }

    @DeleteMapping("/publisher/{publisherID}")
    public void deletePublisherById(@PathVariable("publisherID") Long id)
    {
        publisherService.deleteByID(id);
    }
    @PostMapping("/savePublisher")
    public PublisherResponseDto savePublisher(@RequestBody PublisherResponseDto publisherResponseDTO)
    {
        return  publisherService.create(publisherResponseDTO);
    }
    @PutMapping("/updatePublisher")
    public void updatePublisher(@RequestBody PublisherUpdateDto publisherUpdateDto)    {

        publisherService.update(publisherUpdateDto);
    }







}
