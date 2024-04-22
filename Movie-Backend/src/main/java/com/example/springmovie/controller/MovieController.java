package com.example.springmovie.controller;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.exception.GenreNotFoundException;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import com.example.springmovie.service.interfaces.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor

@Tag(name = "Movie")

@RestController
@RequestMapping("/api/v1/movie")

public class MovieController {

    // TODO: get movies filtered

    private final MovieService movieService;

    // ========================== Find Movies ==========================
    //    @GetMapping("")
    //    @Operation(summary = "Get all movies", description = "Retrieve a paginated list of all movies")
    //    public ResponseEntity<Page<Movie>> findAllMoviesPaged(Pageable pageable) {
    //        return ResponseEntity.ok(movieService.findAllMovies(pageable));
    //    }

    @GetMapping("/{movieId}")
    @Operation(summary = "Get a single movie using id", description = "Retrieve a single movie using an ID passed as a variable")
    public ResponseEntity<Movie> findMovieById(@PathVariable Long movieId) throws MovieNotFoundException {
        return ResponseEntity.ok(movieService.findMovieById(movieId));
    }

    @GetMapping("status/{releaseStatus}")
    @Operation(summary = "Find movies by release status")
    public ResponseEntity<List<Movie>> findMoviesByReleaseStatus(@PathVariable("releaseStatus") ReleaseStatus status) {
        return ResponseEntity.ok().body(movieService.findMoviesByReleaseStatus(status));
    }

    @GetMapping("/year/{year}")
    @Operation(summary = "Get all movies that were released in a year")
    public ResponseEntity<List<Movie>> findMoviesByReleaseYear(@PathVariable int year) {
        return ResponseEntity.ok().body(movieService.findMoviesByYear(year));
    }
    //
    //    @GetMapping("/filter")
    //    public List<Movie> findMoviesByFilter(@RequestParam(required = false) Integer startReleaseDate, @RequestParam(required = false) Integer endReleaseDate,
    //                                          @RequestParam(required = false) Set<Genre> genres, @RequestParam(required = false) String title) {
    //        return movieService.filterMovies(startReleaseDate, endReleaseDate, genres, title);
    //    }

    @GetMapping("/unpaged")
    public ResponseEntity<List<Movie>> findAllMovies() {
        return new ResponseEntity<>(movieService.findAllMovies(), HttpStatus.OK);
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
    public ResponseEntity<?> deleteMovieById(@PathVariable("movieId") Long movieId) throws MovieNotFoundException {
        movieService.deleteMovieById(movieId);
        return ResponseEntity.noContent().build();
    }

    // ========================== Genre Operations ==========================

    @PutMapping("movie/{movieId}/addGenre/{genreId}")
    @Operation(summary = "Add a genre to a movie")
    public ResponseEntity<Movie> addGenreToMovie(@Parameter(description = "ID of movie that the genre will be added to") @PathVariable("movieId") Long movieId,
                                                 @Parameter(description = "ID of the genre that will be added to the movie") @PathVariable("genreId") Long genreId) throws MovieNotFoundException, GenreNotFoundException {
        return new ResponseEntity<>(movieService.addGenreToMovie(movieId, genreId), HttpStatus.OK);
    }

    @PutMapping("movie/{movieId}/removeGenre/{genreId}")
    @Operation(summary = "Remove a genre from a movie")
    public ResponseEntity<Movie> removeGenreFromMovie(@PathVariable("movieId") Long movieId, @PathVariable("genreId") Long genreId) throws MovieNotFoundException,
            GenreNotFoundException {
        return new ResponseEntity<>(movieService.removeGenreFromMovie(movieId, genreId), HttpStatus.OK);
    }

    @GetMapping("movie/allGenres/{movieId}")
    @Operation(summary = "Find all genres of a movie")
    public ResponseEntity<List<Genre>> findGenresOfMovie(@PathVariable("movieId") Long movieId) {
        return new ResponseEntity<>(movieService.findAllGenresOfMovie(movieId), HttpStatus.OK);
    }

    @GetMapping("movie/findByGenreId/{genreId}")
    @Operation(summary = "Find all movies that contain a genre using the genreId")
    public ResponseEntity<List<Movie>> findAllMoviesContainingGenre(@PathVariable("genreId") Long genreId) {
        return ResponseEntity.ok(movieService.findMoviesByGenreId(genreId));
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
        movieService.deleteMoviePoster(movieId);
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
