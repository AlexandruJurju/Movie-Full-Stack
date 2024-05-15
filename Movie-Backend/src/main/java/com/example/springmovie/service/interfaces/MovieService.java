package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.DetailedMovieDto;
import com.example.springmovie.dto.GenreDto;
import com.example.springmovie.dto.MovieDto;
import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Page<MovieDto> findMoviesPaged(Pageable pageable);

    DetailedMovieDto findMovieByIdDetailed(Long movieId) throws MovieNotFoundException;

    List<MovieDto> findAllMoviesPaged();

    Optional<MovieDto> findMovieById(Long id) throws MovieNotFoundException;

    MovieDto saveMovie(MovieDto movieDto);

    void deleteMovieById(Long id) throws MovieNotFoundException;

    List<MovieDto> findMoviesByReleaseStatus(ReleaseStatus status);

    List<MovieDto> findMoviesByYear(int year);

    Optional<MovieDto> addGenreToMovie(Long movieId, Long genreId);

    Optional<MovieDto> removeGenreFromMovie(Long movieId, Long genreId);

    List<GenreDto> findAllGenresOfMovie(Long movieId);

    MovieDto updateMoviePoster(Long movieId, MultipartFile file) throws MovieNotFoundException;

    byte[] getMoviePoster(Long movieId) throws MovieNotFoundException;

    void deleteMoviePoster(Long movieId) throws MovieNotFoundException;

    MovieDto updateMovie(Long movieId, MovieDto movieDto) throws MovieNotFoundException;
}
