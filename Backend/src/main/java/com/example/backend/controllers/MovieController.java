package com.example.backend.controllers;

import com.example.backend.models.Movie;
import com.example.backend.models.ReleaseStatus;
import com.example.backend.services.movieService.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/movie")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public void initMovies() {
        Movie movie = new Movie();
        movie.setBudget(1000);
        movie.setOverview("THE BEST");
        movie.setRuntime(1230);
        movie.setTitle("AVATAR");
        movie.setReleaseStatus(ReleaseStatus.RELEASED);
        service.saveMovie(movie);
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
