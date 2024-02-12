package com.example.springmovie.service;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> saveAll();

    Movie findById(Long id);

    Movie save(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteById(Long id);

    List<Movie> findByGenreId(Long genreId);

    List<Movie> findByGenreName(String genreName);

    List<Movie> findByReleaseStatus(ReleaseStatus status);

    List<Movie> findByYear(int year);

    List<Genre> findAllGenresOfMovie(Long movieId);
}
