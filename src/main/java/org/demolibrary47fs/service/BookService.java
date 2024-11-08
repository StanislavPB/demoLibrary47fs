package org.demolibrary47fs.service;

import lombok.RequiredArgsConstructor;
import org.demolibrary47fs.dto.BookRequestDto;
import org.demolibrary47fs.entity.Author;
import org.demolibrary47fs.entity.Book;
import org.demolibrary47fs.entity.Genre;
import org.demolibrary47fs.repository.BookRepository;
import org.demolibrary47fs.service.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final AuthorService authorService;
    private final GenreService genreService;


    // создать новую книгу

    public Book createBook(BookRequestDto request){

        Author author = authorService.findAuthorByName(request.getAuthor());

        List<Genre> genres = genreService.findGenresByName(request.getGenres());

        Book newBook = Book.builder()
                .title(request.getTitle())
                .issued(false)
                .author(author)
                .genres(genres)
                .build();

        Book savedBook = repository.save(newBook);

        return savedBook;
    }

    public List<Book> findAll(){
        return repository.findAll();
    }

    public List<Book> findBookByAuthor(String authorName){
        Author author = authorService.findAuthorByName(authorName);
        List<Book> booksList = repository.findByAuthor(author);
        return booksList;
    }

    public List<Book> getAvailableBooks(){
        return repository.findByIssuedFalse();
    }


    public List<Book> getIssuedBooks(){
        return repository.findByIssuedTrue();
    }

    public Book getBookById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

}
