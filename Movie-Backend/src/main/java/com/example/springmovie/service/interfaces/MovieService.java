package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.GenreDto;
import com.example.springmovie.dto.MovieDto;
import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Page<Movie> findAllMoviesPaged(Pageable pageable);

    List<MovieDto> findAllMoviesPaged();

    Optional<MovieDto> findMovieById(Long id) throws MovieNotFoundException;

    MovieDto saveMovie(MovieDto movieDto);

    void deleteMovieById(Long id) throws MovieNotFoundException;

    List<MovieDto> findMoviesByReleaseStatus(ReleaseStatus status);

    List<MovieDto> findMoviesByYear(int year);

    Optional<MovieDto> addGenreToMovie(Long movieId, Long genreId);

    Optional<MovieDto> removeGenreFromMovie(Long movieId, Long genreId);

    List<GenreDto> findAllGenresOfMovie(Long movieId);
}
