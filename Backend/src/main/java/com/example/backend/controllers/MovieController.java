package com.example.backend.controllers;

import com.example.backend.models.Genre;
import com.example.backend.models.Movie;
import com.example.backend.models.ReleaseStatus;
import com.example.backend.services.genreService.GenreService;
import com.example.backend.services.movieService.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    private final GenreService genreService;

    public MovieController(MovieService movieService, GenreService genreService) {
        this.movieService = movieService;
        this.genreService = genreService;
    }

    @GetMapping
    public List<Movie> findAllMovies() {
        return movieService.findAllMovies();
    }

    @GetMapping("/{id}")
    public Optional<Movie> findMovieById(@PathVariable("id") Long id) {
        return movieService.findMovieById(id);
    }

    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @PutMapping
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovieById(id);
    }

    @GetMapping("status/{release_status}")
    public List<Movie> findMoviesByReleaseStatus(@PathVariable("release_status") ReleaseStatus status) {
        return movieService.findMovieByReleaseStatus(status);
    }

    @PutMapping("/{movieID}/addGenre/{genreID}")
    public Movie addGenreToMovie(@PathVariable("movieID") Long movieID, @PathVariable("genreID") Long genreID) {
        Genre genre = genreService.findGenreById(genreID).get();
        Movie movie = movieService.findMovieById(movieID).get();

        movie.addGenre(genre);
        return movieService.saveMovie(movie);
    }
}
