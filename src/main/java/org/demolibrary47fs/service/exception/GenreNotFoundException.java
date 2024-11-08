package org.demolibrary47fs.service.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(String genreName) {
        super("Genre with name " + genreName + " not found");
    }
}
