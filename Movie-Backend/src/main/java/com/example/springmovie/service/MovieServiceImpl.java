package com.example.springmovie.service;

import com.example.springmovie.dto.GenreDto;
import com.example.springmovie.dto.MovieDto;
import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.mappers.GenreMapper;
import com.example.springmovie.mappers.MovieMapper;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import com.example.springmovie.repositories.GenreRepository;
import com.example.springmovie.repositories.MovieRepository;
import com.example.springmovie.service.interfaces.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    private final MovieMapper movieMapper;
    private final GenreMapper genreMapper;

    @Override
    public Page<Movie> findAllMoviesPaged(Pageable pageable) {
        log.debug("Find all movies with pagination");
        return movieRepository.findAll(pageable);
    }

    @Override
    public List<MovieDto> findAllMoviesPaged() {
        log.debug("Find all movies unpaged");
        List<Movie> movies = movieRepository.findAll();
        return movieMapper.toDto(movies);
    }

    @Override
    public Optional<MovieDto> findMovieById(Long id) {
        log.debug("Find movie by id {}", id);
        return movieRepository.findById(id).map(movieMapper::toDto);
    }

    @Override
    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = movieMapper.toEntity(movieDto);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toDto(savedMovie);
    }

    @Override
    public void deleteMovieById(Long id) throws MovieNotFoundException {
        log.debug("Deleting movie by id: {}", id);

        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException("Movie with id " + id + " not found");
        }
        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieDto> findMoviesByReleaseStatus(ReleaseStatus status) {
        log.debug("Find movies by status {}", status);
        List<Movie> movies = movieRepository.findMovieByReleaseStatus(status);
        return movieMapper.toDto(movies);
    }

    @Override
    public List<MovieDto> findMoviesByYear(int year) {
        log.debug("Find movie by year {}", year);
        List<Movie> movies = movieRepository.findMoviesByYear(year);
        return movieMapper.toDto(movies);
    }

    @Override
    public Optional<MovieDto> addGenreToMovie(Long movieId, Long genreId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        Optional<Genre> genreOptional = genreRepository.findById(genreId);

        if (movieOptional.isPresent() && genreOptional.isPresent()) {
            Movie movie = movieOptional.get();
            Genre genre = genreOptional.get();

            movie.getGenres().add(genre);
            Movie savedMovie = movieRepository.save(movie);
            return Optional.of(movieMapper.toDto(savedMovie));
        } else {
            return Optional.empty();
        }

    }

    @Override
    public Optional<MovieDto> removeGenreFromMovie(Long movieId, Long genreId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        Optional<Genre> genreOptional = genreRepository.findById(genreId);

        if (movieOptional.isPresent() && genreOptional.isPresent()) {
            Movie movie = movieOptional.get();
            Genre genre = genreOptional.get();

            movie.getGenres().remove(genre);
            Movie savedMovie = movieRepository.save(movie);
            return Optional.of(movieMapper.toDto(savedMovie));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<GenreDto> findAllGenresOfMovie(Long movieId) {
        return genreMapper.toDto(movieRepository.findGenresByMovieId(movieId));
    }

}
