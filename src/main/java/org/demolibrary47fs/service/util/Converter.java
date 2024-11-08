package org.demolibrary47fs.service.util;

import org.demolibrary47fs.dto.AuthorRequestDto;
import org.demolibrary47fs.dto.BookRequestDto;
import org.demolibrary47fs.dto.GenreRequestDto;
import org.demolibrary47fs.entity.Author;
import org.demolibrary47fs.entity.Book;
import org.demolibrary47fs.entity.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Converter {

    public Book getBookFromDto(BookRequestDto request, Author author, List<Genre> genres){

        return Book.builder()
                .title(request.getTitle())
                .author(author)
                .genres(genres)
                .issued(false)
                .build();

    }

    public Author getAuthorFromDto(AuthorRequestDto request){
        return Author.builder()
                .name(request.getName())
                .build();
    }

    public Genre getGenreFromDto(GenreRequestDto request){
        return Genre.builder()
                .name(request.getName())
                .build();
    }



}
