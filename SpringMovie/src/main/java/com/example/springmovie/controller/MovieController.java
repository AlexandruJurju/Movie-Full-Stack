package com.example.springmovie.controller;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.exception.NotFoundException;
import com.example.springmovie.exception.ResourceAlreadyExistsException;
import com.example.springmovie.model.Movie;
import com.example.springmovie.service.S3FileService;
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
// todo: movie exceptions
@Tag(name = "Movie Controller", description = "CRUD REST APIs for managing movies")

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieService movieService;
    private final S3FileService s3FileService;


    @GetMapping("")
    @Operation(summary = "Get all movies", description = "Retrieve a paginated list of all movies")
    public ResponseEntity<Page<Movie>> findAllMovies(Pageable pageable) {
        Page<Movie> movies = movieService.findAllMovies(pageable);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "Get a single movie using id", description = "Retrieve a single movie using an ID passed as a variable")
    public ResponseEntity<Movie> findMovieById(@PathVariable Long movieId) {
        try {
            Movie movie = movieService.findMovieById(movieId);
            return ResponseEntity.ok(movie);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    @Operation(summary = "Save a movie")
    public ResponseEntity<Movie> saveMovie(@RequestBody @Valid Movie movie) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(movieService.saveMovie(movie));
        } catch (ResourceAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping
    @Operation(summary = "Update a Movie", description = "REST API to update a Movie based using RequestBody")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        try {
            return ResponseEntity.ok(movieService.saveMovie(movie));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{movieId}")
    @Operation(summary = "Delete a Movie", description = "REST API to delete a Movie using an id passed as a variable")
    public ResponseEntity<?> deleteMovie(@PathVariable("movieId") Long movieId) {
        try {
            movieService.deleteMovieById(movieId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("status/{releaseStatus}")
    @Operation(summary = "Find movies by release status")
    public ResponseEntity<List<Movie>> findMoviesByReleaseStatus(@PathVariable("releaseStatus") ReleaseStatus status) {
        List<Movie> movies = movieService.findMovieByReleaseStatus(status);
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/year/{year}")
    @Operation(summary = "Get all movies that were released in a year")
    public ResponseEntity<List<Movie>> findMoviesByReleaseYear(@PathVariable int year) {
        List<Movie> movies = movieService.findMovieByYear(year);
        return ResponseEntity.ok().body(movies);
    }

    // ==========================

    @PostMapping(value = "/saveWithPoster", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Save a movie together with its poster")
    public ResponseEntity<Movie> saveMovieWithPoster(Movie movie, @RequestParam(name = "file", required = false) MultipartFile file) {
        String key = s3FileService.upload(file);
        movie.setPosterUrl(key);
        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.CREATED);
    }


    @PostMapping(value = "/poster", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update the poster of a movie")
    public Movie updateMoviePoster(@RequestParam("movieId") Long movieId, @RequestParam(value = "file") MultipartFile file) {
        Movie movie = movieService.findMovieById(movieId);
        String moviePosterURL = movie.getPosterUrl();
        if (moviePosterURL != null) {
            s3FileService.delete(moviePosterURL);
        }
        movie.setPosterUrl(s3FileService.upload(file));
        return movieService.saveMovie(movie);
    }

    @PutMapping("/{movieId}/poster/delete")
    @Operation(summary = "Delete a poster from a movie")
    public Movie deletePoster(@PathVariable("movieId") Long movieId) {
        Movie movie = movieService.findMovieById(movieId);
        String moviePosterURL = movie.getPosterUrl();
        if (moviePosterURL != null) {
            s3FileService.delete(moviePosterURL);
        }
        movie.setPosterUrl(null);
        return movieService.saveMovie(movie);
    }

    @GetMapping("/{movieId}/poster")
    @Operation(summary = "Get the poster image from a movie")
    public ResponseEntity<byte[]> getMoviePoster(@PathVariable("movieId") Long movieId) {
        Movie movie = movieService.findMovieById(movieId);
        byte[] image = s3FileService.download(movie.getPosterUrl());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
        //        return s3FileService.download(movie.getPosterURL());
    }


}
