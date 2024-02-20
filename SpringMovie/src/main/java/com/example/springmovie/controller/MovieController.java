package com.example.springmovie.controller;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.model.Movie;
import com.example.springmovie.service.interfaces.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor

@Tag(name = "Movie Controller", description = "CRUD REST APIs for managing movies")

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    // ========================== Find Movies ==========================
    @GetMapping("")
    @Operation(summary = "Get all movies", description = "Retrieve a paginated list of all movies")
    public ResponseEntity<Page<Movie>> findAllMovies(Pageable pageable) {
        Page<Movie> movies = movieService.findAllMovies(pageable);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "Get a single movie using id", description = "Retrieve a single movie using an ID passed as a variable")
    public ResponseEntity<Movie> findMovieById(@PathVariable Long movieId) throws MovieNotFoundException {
        Movie movie = movieService.findMovieById(movieId);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("status/{releaseStatus}")
    @Operation(summary = "Find movies by release status")
    public ResponseEntity<List<Movie>> findMoviesByReleaseStatus(@PathVariable("releaseStatus") ReleaseStatus status) {
        List<Movie> movies = movieService.findMoviesByReleaseStatus(status);
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/year/{year}")
    @Operation(summary = "Get all movies that were released in a year")
    public ResponseEntity<List<Movie>> findMoviesByReleaseYear(@PathVariable int year) {
        List<Movie> movies = movieService.findMoviesByYear(year);
        return ResponseEntity.ok().body(movies);
    }

    // ========================== CRUD Operations ==========================

    @PostMapping
    @Operation(summary = "Save a movie")
    public ResponseEntity<Movie> saveMovie(@RequestBody @Valid Movie movie) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.saveMovie(movie));
    }

    @PutMapping
    @Operation(summary = "Update a Movie", description = "REST API to update a Movie based using RequestBody")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.saveMovie(movie));
    }

    @DeleteMapping("/{movieId}")
    @Operation(summary = "Delete a Movie", description = "REST API to delete a Movie using an id passed as a variable")
    public ResponseEntity<?> deleteMovie(@PathVariable("movieId") Long movieId) throws MovieNotFoundException {
        movieService.deleteMovieById(movieId);
        return ResponseEntity.noContent().build();
    }

    // ========================== Poster Operations ==========================

    @PostMapping(value = "/saveWithPoster", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Save a movie together with its poster")
    public ResponseEntity<Movie> saveMovieWithPoster(Movie movie, @RequestParam(name = "file", required = false) MultipartFile file) {
        return new ResponseEntity<>(movieService.saveMovieWithPoster(movie, file), HttpStatus.CREATED);
    }

    @PostMapping(value = "/poster", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update the poster of a movie")
    public ResponseEntity<Movie> updateMoviePoster(@RequestParam("movieId") Long movieId, @RequestParam(value = "file") MultipartFile file) throws MovieNotFoundException {
        return ResponseEntity.ok().body(movieService.updateMoviePoster(movieId, file));
    }

    @PutMapping("/{movieId}/poster/delete")
    @Operation(summary = "Delete a poster from a movie")
    public ResponseEntity<Object> deletePoster(@PathVariable("movieId") Long movieId) throws MovieNotFoundException {
        movieService.deleteMoviePoster(movieId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{movieId}/poster")
    @Operation(summary = "Get the poster image from a movie")
    public ResponseEntity<byte[]> getMoviePoster(@PathVariable("movieId") Long movieId) throws MovieNotFoundException {
        byte[] image = movieService.getMoviePoster(movieId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }
}
