package org.demolibrary47fs.service.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book with ID " + id + " not found");
    }
}
