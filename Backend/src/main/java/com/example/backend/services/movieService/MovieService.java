package com.example.backend.services.movieService;

import com.example.backend.model.Movie;
import com.example.backend.model.ReleaseStatus;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> findAllMovies();

    Optional<Movie> findMovieById(Long id);

    Movie saveMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovieById(Long id);

    List<Movie> findMovieByReleaseStatus(ReleaseStatus status);
}
