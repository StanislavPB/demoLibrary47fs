package org.demolibrary47fs.service;

import lombok.RequiredArgsConstructor;
import org.demolibrary47fs.dto.AuthorRequestDto;
import org.demolibrary47fs.dto.GenreRequestDto;
import org.demolibrary47fs.entity.Author;
import org.demolibrary47fs.entity.Genre;
import org.demolibrary47fs.repository.AuthorRepository;
import org.demolibrary47fs.repository.GenreRepository;
import org.demolibrary47fs.service.exception.AlreadyExistException;
import org.demolibrary47fs.service.exception.AuthorNotFoundException;
import org.demolibrary47fs.service.util.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final Converter converter;

    @Transactional
    public Author addNewAuthor(AuthorRequestDto request){

        if (checkNotExistAuthor(request.getName())) {
            Author newAuthor = converter.getAuthorFromDto(request);
            return authorRepository.save(newAuthor);
        } else {
            throw new AlreadyExistException("Author with name: " + request.getName() + " is already exist");
        }


    }

    private boolean checkNotExistAuthor(String authorName){

        Optional<Author> foundedAuthor =  authorRepository.findByName(authorName);

        return foundedAuthor.isEmpty();
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findAuthorById(Long id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author with ID: " + id + " not found"));
    }

    public Author findAuthorByName(String authorName){
        return authorRepository.findByName(authorName)
                .orElseThrow(() -> new AuthorNotFoundException("Author with name: " + authorName + " not found"));
    }

}
