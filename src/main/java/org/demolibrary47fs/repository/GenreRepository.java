package org.demolibrary47fs.repository;

import org.demolibrary47fs.entity.Author;
import org.demolibrary47fs.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByName(String name);
}
