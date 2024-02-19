package com.example.springmovie.service;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.exception.NotFoundException;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import com.example.springmovie.repositories.MovieRepository;
import com.example.springmovie.service.interfaces.GenreService;
import com.example.springmovie.service.interfaces.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreService genreService;

    // TODO: add filtering

    @Override
    public Page<Movie> findAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public Movie findMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        Optional<Movie> existingMovie = movieRepository.findById(movie.getId());
        if (existingMovie.isEmpty()) {
            throw new NotFoundException(movie.getId());
        }
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovieById(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> findMovieByGenreId(Long genreId) {
        return movieRepository.findMoviesByGenreId(genreId);
    }

    @Override
    public List<Movie> findMovieByReleaseStatus(ReleaseStatus status) {
        return movieRepository.findMovieByReleaseStatus(status);
    }

    @Override
    public List<Movie> findMovieByYear(int year) {
        return movieRepository.findMoviesByYear(year);
    }

    @Override
    public List<Genre> findAllGenresOfMovie(Long movieId) {
        return movieRepository.findGenresByMovieId(movieId);
    }

    @Override
    public Movie addGenreToMovie(Long movieId, Long genreId) {
        Movie movie = movieRepository.findById(movieId).
                orElseThrow();
        Genre genre = genreService.findGenreById(genreId);
        movie.getGenres().add(genre);
        return movieRepository.save(movie);
    }

    @Override
    public Movie removeGenreFromMovie(Long movieId, Long genreId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        Genre genre = genreService.findGenreById(genreId);
        movie.getGenres().remove(genre);
        return movieRepository.save(movie);
    }
}
