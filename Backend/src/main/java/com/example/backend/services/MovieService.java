package com.example.backend.services;

import com.example.backend.enums.ReleaseStatus;
import com.example.backend.model.Genre;
import com.example.backend.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> findAllMovies();

    Movie findMovieById(Long id);

    Movie saveMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovieById(Long id);

    List<Movie> findMovieByReleaseStatus(ReleaseStatus status);

    List<Movie> findMoviesByYear(int year);

    List<Genre> findAllGenresOfAMovie(Long movieId);
}
