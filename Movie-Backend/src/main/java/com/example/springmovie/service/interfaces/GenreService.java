package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.GenreDto;
import com.example.springmovie.dto.MovieDto;
import com.example.springmovie.exception.GenreNotFoundException;
import com.example.springmovie.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<GenreDto> findAllGenres();

    Optional<GenreDto> findGenreById(Long id);

    GenreDto saveGenre(GenreDto genreDto);

    void deleteGenre(Long id) throws GenreNotFoundException;

    List<MovieDto> findAllMoviesWithGenre(Long genreId);
}
