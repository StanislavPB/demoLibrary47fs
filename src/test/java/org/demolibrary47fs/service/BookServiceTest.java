package org.demolibrary47fs.service;

import org.demolibrary47fs.entity.Book;
import org.demolibrary47fs.repository.AuthorRepository;
import org.demolibrary47fs.repository.BookRepository;
import org.demolibrary47fs.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {


    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private GenreRepository genreRepository;


    @Test
    void testGetBookById(){
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book foundedBook = service.getBookById(bookId);
        assertNotNull(foundedBook);
        assertEquals(bookId, foundedBook.getId());
    }


}