package com.example.springmovie.service.interfaces;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    Page<Movie> findAllMovies(Pageable pageable);

    Movie findMovieById(Long id);

    Movie saveMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovieById(Long id);

    List<Movie> findMovieByGenreId(Long genreId);

    List<Movie> findMovieByReleaseStatus(ReleaseStatus status);

    List<Movie> findMovieByYear(int year);

    List<Genre> findAllGenresOfMovie(Long movieId);

    Movie addGenreToMovie(Long movieId, Long genreId);

    Movie removeGenreFromMovie(Long movieId, Long genreId);
}
