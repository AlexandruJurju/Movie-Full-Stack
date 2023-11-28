package com.example.backend.controllers;

import com.example.backend.models.Genre;
import com.example.backend.models.Movie;
import com.example.backend.models.ReleaseStatus;
import com.example.backend.services.movieService.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/movie")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Movie> findAllMovies() {
        return service.findAllMovies();
    }

    @GetMapping("/{id}")
    public Optional<Movie> findMovieById(@PathVariable("id") Long id) {
        return service.findMovieById(id);
    }

    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return service.saveMovie(movie);
    }

    @PutMapping
    public Movie updateMovie(@RequestBody Movie movie) {
        return service.saveMovie(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") Long id) {
        service.deleteMovieById(id);
    }

    @GetMapping("/{release_status}")
    public List<Movie> findMoviesByReleaseStatus(@PathVariable("release_status") ReleaseStatus status) {
        return service.findMovieByReleaseStatus(status);
    }
}
