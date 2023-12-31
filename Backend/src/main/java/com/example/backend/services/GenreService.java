package com.example.backend.services;

import com.example.backend.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAllGenres();

    Genre findGenreById(Long id);

    Genre saveGenre(Genre genre);

    void deleteGenre(Long id);
}
