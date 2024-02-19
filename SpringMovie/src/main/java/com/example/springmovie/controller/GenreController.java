package com.example.springmovie.controller;

import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import com.example.springmovie.service.interfaces.GenreService;
import com.example.springmovie.service.interfaces.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/genre")
// TODO: genre exceptions
@Tag(name = "Genre Controller", description = "CRUD REST APIs for managing movie genres")

public class GenreController {

    private final GenreService genreService;
    private final MovieService movieService;

    @GetMapping
    @Operation(summary = "Find all genres")
    public ResponseEntity<List<Genre>> getAllGenres() {
        return new ResponseEntity<>(genreService.findAllGenres(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a genre using Id")
    public ResponseEntity<Genre> findGenreById(@PathVariable("id") Long id) {
        Genre genre = genreService.findGenreById(id);
        if (genre != null) {
            return new ResponseEntity<>(genre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Save genre to database")
    public ResponseEntity<Genre> saveGenre(@RequestBody Genre genre) {
        Genre savedGenre = genreService.saveGenre(genre);
        return new ResponseEntity<>(savedGenre, HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update genre")
    public ResponseEntity<Genre> updateGenre(@RequestBody Genre genre) {
        Genre updatedGenre = genreService.saveGenre(genre);
        return new ResponseEntity<>(updatedGenre, HttpStatus.OK);
    }

    @DeleteMapping("/{genreId}")
    @Operation(summary = "Delete genre using ID")
    @ApiResponse(responseCode = "204", description = "Genre deleted successfully")
    public ResponseEntity<?> deleteGenreById(@PathVariable("genreId") Long genreId) {
        genreService.deleteGenre(genreId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("movie/{movieId}")
    @Operation(summary = "Find all genres of a movie")
    public ResponseEntity<List<Genre>> findAllGenresOfAMovie(@PathVariable("movieId") Long movieId) {
        List<Genre> genres = movieService.findAllGenresOfMovie(movieId);
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @PutMapping("movie/{movieId}/addGenre/{genreId}")
    @Operation(summary = "Add a genre to a movie")
    public ResponseEntity<Movie> addGenreToMovie(@Parameter(description = "ID of movie that the genre will be added to") @PathVariable("movieId") Long movieId,
                                                 @Parameter(description = "ID of the genre that will be added to the movie") @PathVariable("genreId") Long genreId) {
        Movie movie = movieService.addGenreToMovie(movieId, genreId);
        return ResponseEntity.ok(movie);
    }

    @PutMapping("movie/{movieId}/removeGenre/{genreId}")
    @Operation(summary = "Remove a genre from a movie")
    public ResponseEntity<Movie> removeGenreFromMovie(@PathVariable("movieId") Long movieId, @PathVariable("genreId") Long genreId) {
        Movie movie = movieService.removeGenreFromMovie(movieId, genreId);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("movie/findByGenreId/{genreId}")
    @Operation(summary = "Find all movies that contain a genre using the genreId")
    public ResponseEntity<List<Movie>> findAllMoviesContainingGenre(@PathVariable("genreId") Long genreId) {
        List<Movie> movies = movieService.findMovieByGenreId(genreId);
        return ResponseEntity.ok(movies);
    }

}
