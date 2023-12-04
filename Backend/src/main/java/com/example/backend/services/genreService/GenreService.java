package com.example.backend.services.genreService;

import com.example.backend.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> findAllGenres();

    Optional<Genre> findGenreById(Long id);

    Genre saveGenre(Genre genre);

    void deleteGenre(Long id);
}
