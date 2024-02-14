package com.example.springmovie.controller;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.model.Movie;
import com.example.springmovie.service.MovieService;
import com.example.springmovie.service.impl.S3FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Slf4j

@Tag(name = "Movie Controller", description = "CRUD REST APIs for managing movies")

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;
    private final S3FileService s3FileService;


    @GetMapping("")
    @Operation(summary = "Get all movies", description = "Retrieve a list of all movies")
    public List<Movie> findAllMovies() {
        return movieService.saveAll();
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "Get a single movie using id", description = "Retrieve a single movie using an ID passed as a variable")
    public Movie findMovieById(@Parameter(description = "id of movie to be searched") @PathVariable(value = "movieId") Long movieId) {
        return movieService.findById(movieId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Save a movie", description = "REST API to save a movie based using RequestBody")
    public ResponseEntity<Movie> saveMovie(Movie movie, @RequestParam(value = "file") MultipartFile file) {
        String path = s3FileService.upload(file);
        movie.setPosterURL(path);
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.CREATED);
    }

    @PostMapping
    @Operation(summary = "Save a movie")
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping
    @Operation(summary = "Update a Movie", description = "REST API to update a Movie based using RequestBody")
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @DeleteMapping("/{movieId}")
    @Operation(summary = "Delete a Movie", description = "REST API to delete a Movie using an id passed as a variable")
    public void deleteMovie(@PathVariable("movieId") Long movieId) {
        movieService.deleteById(movieId);
    }

    @GetMapping("status/{releaseStatus}")
    @Operation(summary = "Find movies by release status")
    public List<Movie> findMoviesByReleaseStatus(@PathVariable("releaseStatus") ReleaseStatus status) {
        return movieService.findByReleaseStatus(status);
    }

    @PutMapping("/{movieId}/poster/delete")
    @Operation(summary = "Delete a poster from a movie")
    public Movie deletePoster(@PathVariable("movieId") Long movieId) {
        Movie movie = movieService.findById(movieId);
        String moviePosterURL = movie.getPosterURL();
        if (moviePosterURL != null) {
            s3FileService.delete(moviePosterURL);
        }
        movie.setPosterURL(null);
        return movieService.save(movie);
    }

    @GetMapping("/{movieId}/poster")
    @Operation(summary = "Get the poster image from a movie")
    public ResponseEntity<byte[]> getMoviePoster(@PathVariable("movieId") Long movieId) {
        Movie movie = movieService.findById(movieId);
        byte[] image = s3FileService.download(movie.getPosterURL());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
        //        return s3FileService.download(movie.getPosterURL());
    }

    @PostMapping(value = "/poster", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update a poster of a movie")
    public Movie updateMoviePoster(@RequestParam("movieId") Long movieId, @RequestParam(value = "file") MultipartFile file) {
        Movie movie = movieService.findById(movieId);
        String moviePosterURL = movie.getPosterURL();
        if (moviePosterURL != null) {
            s3FileService.delete(moviePosterURL);
        }
        movie.setPosterURL(s3FileService.upload(file));
        log.info(movie.getPosterURL());
        return movieService.save(movie);
    }

    @GetMapping("/year/{year}")
    @Operation(summary = "Get all movies that were released in a year")
    public List<Movie> getMoviesInYear(@PathVariable("year") int year) {
        return movieService.findByYear(year);
    }


}
