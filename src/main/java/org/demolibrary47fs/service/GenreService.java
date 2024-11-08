package org.demolibrary47fs.service;

import lombok.RequiredArgsConstructor;
import org.demolibrary47fs.dto.GenreRequestDto;
import org.demolibrary47fs.entity.Genre;
import org.demolibrary47fs.repository.GenreRepository;
import org.demolibrary47fs.service.exception.AlreadyExistException;
import org.demolibrary47fs.service.exception.GenreNotFoundException;
import org.demolibrary47fs.service.util.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    private final Converter converter;

    @Transactional
    public Genre addNewGenre(GenreRequestDto request) {

        if (checkNotExistGenre(request.getName())) {
            Genre newGenre = converter.getGenreFromDto(request);
            return genreRepository.save(newGenre);
        } else {
            throw new AlreadyExistException("Genre with name: " + request.getName() + " is already exist");
        }


    }

    private boolean checkNotExistGenre(String genreName) {

        List<Genre> foundedGenres = genreRepository.findByName(genreName);

        return foundedGenres.isEmpty();
    }


    public List<Genre> findAll() {
        return genreRepository.findAll();
    }


    public List<Genre> findGenresByName(List<String> genreNames) {

        List<Genre> listOfGenre = new ArrayList<>();

        for (String currentName : genreNames) {
            List<Genre> foundedGenres = genreRepository.findByName(currentName);
            if (foundedGenres.isEmpty()) {
                throw new GenreNotFoundException(currentName);
            }
            listOfGenre.addAll(foundedGenres);
        }


        return listOfGenre;
    }
}
