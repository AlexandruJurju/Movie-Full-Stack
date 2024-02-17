package com.example.springmovie.controller;

import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import com.example.springmovie.service.interfaces.GenreService;
import com.example.springmovie.service.interfaces.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public List<Genre> getAllGenres() {
        return genreService.findAllGenres();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a genre using Id")
    public Genre findGenreById(@PathVariable("id") Long id) {
        return genreService.findGenreById(id);
    }

    @PostMapping
    @Operation(summary = "Save genre to database")
    public Genre saveGenre(@RequestBody Genre genre) {
        return genreService.saveGenre(genre);
    }

    @PutMapping
    @Operation(summary = "Update genre")
    public Genre updateGenre(@RequestBody Genre genre) {
        return genreService.saveGenre(genre);
    }

    @DeleteMapping("/{genreId}")
    @Operation(summary = "Delete genre using ID")
    public void deleteGenreById(@PathVariable("genreId") Long genreId) {
        genreService.deleteGenre(genreId);
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "Find all genres of a movie")
    public List<Genre> findAllGenresOfAMovie(@PathVariable("movieId") Long movieId) {
        return movieService.findAllGenresOfMovie(movieId);
    }

    @PutMapping("/{movieId}/addGenre/{genreId}")
    @Operation(summary = "Add a genre to a movie")
    public Movie addGenreToMovie(@Parameter(description = "id of movie that the genre will be added to") @PathVariable("movieId") Long movieId,
                                 @Parameter(description = "id of the genre that will be added to the movie") @PathVariable("genreId") Long genreId) {
        Genre genre = genreService.findGenreById(genreId);
        Movie movie = movieService.findMovieById(movieId);
        movie.getGenres().add(genre);
        return movieService.saveMovie(movie);
    }

    @GetMapping("/findByGenreName/{genreName}")
    @Operation(summary = "Find all movies with genre name")
    public List<Movie> findAllMoviesWithGenreName(@PathVariable("genreName") String genreName) {
        return movieService.findMovieByGenreName(genreName);
    }

    @PutMapping("/{movieId}/removeGenre/{genreId}")
    @Operation(summary = "Remove a genre from a movie")
    public Movie removeGenreFromMovie(@PathVariable("movieId") Long movieId, @PathVariable("genreId") Long genreId) {
        Genre genre = genreService.findGenreById(genreId);
        Movie movie = movieService.findMovieById(movieId);
        movie.getGenres().remove(genre);
        return movieService.saveMovie(movie);
    }

    @GetMapping("/findByGenreId/{genreId}")
    @Operation(summary = "Find all movies that contain a genre using the genreId")
    public List<Movie> findAllMoviesWithGenreID(@PathVariable("genreId") Long genreId) {
        return movieService.findMovieByGenreId(genreId);
    }

}
