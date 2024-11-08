package org.demolibrary47fs.service;

import org.demolibrary47fs.dto.BookRequestDto;
import org.demolibrary47fs.entity.Author;
import org.demolibrary47fs.entity.Book;
import org.demolibrary47fs.entity.Genre;
import org.demolibrary47fs.repository.AuthorRepository;
import org.demolibrary47fs.repository.BookRepository;
import org.demolibrary47fs.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    @Mock
    private GenreService genreService;

    private Author author;
    private Genre genre;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setId(1L);
        author.setName("Author name");

        genre = new Genre();
        genre.setName("Classic");
    }

    @Test
    void testCreateBookWithSingleGenre() {
        BookRequestDto request = new BookRequestDto("Book 1", author.getName(), Arrays.asList("Classic"));

        when(authorService.findAuthorByName(author.getName())).thenReturn(author);
        when(genreService.findGenresByName(request.getGenres())).thenReturn(Arrays.asList(genre));


        Book bookToSave = Book.builder()
                .title(request.getTitle())
                .issued(false)
                .author(author)
                .genres(List.of(genre))
                .build();


        when(bookRepository.save(any(Book.class))).thenReturn(bookToSave);

        // выполнение работы метода
        Book savedBook = bookService.createBook(request);

        // проверка (assert)
        assertNotNull(savedBook);
        assertEquals("Book 1", savedBook.getTitle());
        assertTrue(savedBook.getGenres().contains(genre));
    }
}