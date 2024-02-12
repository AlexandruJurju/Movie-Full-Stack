package com.example.springmovie.service.impl;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import com.example.springmovie.repositories.MovieRepository;
import com.example.springmovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> saveAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Cannot find movie with id - " + id));
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> findByGenreId(Long genreId) {
        return movieRepository.findMoviesByGenreId(genreId);
    }

    @Override
    public List<Movie> findByGenreName(String genreName) {
        return movieRepository.findMoviesByGenreName(genreName);
    }

    @Override
    public List<Movie> findByReleaseStatus(ReleaseStatus status) {
        return movieRepository.findMovieByReleaseStatus(status);
    }

    @Override
    public List<Movie> findByYear(int year) {
        return movieRepository.findMoviesByYear(year);
    }

    @Override
    public List<Genre> findAllGenresOfMovie(Long movieId) {
        return movieRepository.findGenresByMovieId(movieId);
    }
}
