package com.example.springmovie.service.interfaces;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.exception.GenreNotFoundException;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface MovieService {

    Page<Movie> findAllMovies(Pageable pageable);

    List<Movie> findAllMovies();

    List<Movie> filterMovies(Integer startReleaseYear, Integer endReleaseYear, Set<Genre> genres, String title);

    Movie findMovieById(Long id) throws MovieNotFoundException;

    Movie saveMovie(Movie movie);

    Movie updateMovie(Movie movie) throws MovieNotFoundException;

    void deleteMovieById(Long id) throws MovieNotFoundException;

    List<Movie> findMoviesByGenre(Long genreId);

    List<Movie> findMoviesByReleaseStatus(ReleaseStatus status);

    List<Movie> findMoviesByYear(int year);

    List<Genre> findAllGenresOfMovie(Long movieId);

    Movie saveMovieWithPoster(Movie movie, MultipartFile file);

    Movie updateMoviePoster(Long movieId, MultipartFile file) throws MovieNotFoundException;

    void deleteMoviePoster(Long movieId) throws MovieNotFoundException;

    byte[] getMoviePoster(Long movieId) throws MovieNotFoundException;

    Movie addGenreToMovie(Long movieId, Long genreId) throws MovieNotFoundException, GenreNotFoundException;

    Movie removeGenreFromMovie(Long movieId, Long genreId) throws MovieNotFoundException, GenreNotFoundException;
}
