package org.demolibrary47fs.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.demolibrary47fs.dto.AuthorRequestDto;
import org.demolibrary47fs.entity.Author;
import org.demolibrary47fs.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService service;

    @GetMapping
    public List<Author> getAllAuthors(){
        return service.findAll();
    }

    @PostMapping
    public Author createAuthor(@Valid @RequestBody AuthorRequestDto request){
        return service.addNewAuthor(request);
    }
}
