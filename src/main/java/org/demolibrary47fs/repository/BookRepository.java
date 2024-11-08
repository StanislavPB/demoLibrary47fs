package org.demolibrary47fs.repository;

import org.demolibrary47fs.entity.Author;
import org.demolibrary47fs.entity.Book;
import org.demolibrary47fs.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
   //найти книги по жанру
    List<Book> findByGenres_Name(String genreName);

    // найти книги по автору
    List<Book> findByAuthor(Author author);

    // найти книги по названию
    List<Book> findByTitleContainingIgnoreCase(String title);

    // найти книги которые не выданы
    List<Book> findByIssuedFalse();

    // найти книг, которые выданы
    List<Book> findByIssuedTrue();


}
