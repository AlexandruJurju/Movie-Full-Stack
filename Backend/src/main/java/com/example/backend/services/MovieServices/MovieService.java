package com.example.backend.services.MovieServices;

import com.example.backend.models.Movie;
import com.example.backend.models.ReleaseStatus;
import org.springframework.stereotype.Service;

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
