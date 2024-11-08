package org.demolibrary47fs.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.demolibrary47fs.dto.AuthorRequestDto;
import org.demolibrary47fs.dto.GenreRequestDto;
import org.demolibrary47fs.entity.Author;
import org.demolibrary47fs.entity.Genre;
import org.demolibrary47fs.service.AuthorService;
import org.demolibrary47fs.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService service;

    @GetMapping
    public List<Genre> getAllGenres(){
        return service.findAll();
    }

    @PostMapping
    public Genre createGenre(@Valid @RequestBody GenreRequestDto request){
        return service.addNewGenre(request);
    }
}
