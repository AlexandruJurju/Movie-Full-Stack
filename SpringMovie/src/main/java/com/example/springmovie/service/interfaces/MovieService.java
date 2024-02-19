package com.example.springmovie.service.interfaces;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {

    Page<Movie> findAllMovies(Pageable pageable);

    Movie findMovieById(Long id);

    Movie saveMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovieById(Long id);

    List<Movie> findMoviesByGenre(Long genreId);

    List<Movie> findMoviesByReleaseStatus(ReleaseStatus status);

    List<Movie> findMoviesByYear(int year);

    List<Genre> findAllGenresOfMovie(Long movieId);

    Movie saveMovieWithPoster(Movie movie, MultipartFile file);

    Movie updateMoviePoster(Long movieId, MultipartFile file) throws MovieNotFoundException;

    void deleteMoviePoster(Long movieId) throws MovieNotFoundException;

    byte[] getMoviePoster(Long movieId) throws MovieNotFoundException;
}
