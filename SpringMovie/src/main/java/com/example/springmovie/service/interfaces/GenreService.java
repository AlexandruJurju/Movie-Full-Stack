package com.example.springmovie.service.interfaces;

import com.example.springmovie.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAllGenres();

    Genre findGenreById(Long id);

    Genre saveGenre(Genre genre);

    void deleteGenre(Long id);
}
