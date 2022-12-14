package com.example.bookstore.controllers;

import com.example.bookstore.dto.publisher.PublisherRequestDto;
import com.example.bookstore.dto.publisher.PublisherResponseDto;
import com.example.bookstore.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/publishers")
public class PublisherController {
    private final PublisherService publisherService;
    @Autowired
    public PublisherController(PublisherService publisherService){
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
    public void savePublisher(@RequestBody PublisherResponseDto publisherResponseDTO)
    {
        publisherService.create(publisherResponseDTO);
    }
    @PutMapping("/updatePublisher")
    public void updatePublisher(@RequestBody PublisherResponseDto publisherResponseDto)    {

        publisherService.update(publisherResponseDto);
    }







}
