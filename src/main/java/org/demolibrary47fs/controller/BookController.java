package org.demolibrary47fs.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.demolibrary47fs.dto.BookRequestDto;
import org.demolibrary47fs.dto.GenreRequestDto;
import org.demolibrary47fs.entity.Book;
import org.demolibrary47fs.entity.Genre;
import org.demolibrary47fs.service.BookService;
import org.demolibrary47fs.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping
    public List<Book> getAllBooks(){
        return service.findAll();
    }

    @PostMapping
    public Book createBook(@Valid @RequestBody BookRequestDto request){
        return service.createBook(request);
    }

    @GetMapping("/available")
    public List<Book> getAvailableBooks(){
        return service.getAvailableBooks();
    }

    @GetMapping("/issued")
    public List<Book> getIssuedBooks(){
        return service.getIssuedBooks();
    }
}
