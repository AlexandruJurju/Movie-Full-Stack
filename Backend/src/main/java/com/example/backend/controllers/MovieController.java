package com.example.backend.controllers;

import com.example.backend.models.Genre;
import com.example.backend.models.Movie;
import com.example.backend.models.ReleaseStatus;
import com.example.backend.services.genreService.GenreService;
import com.example.backend.services.movieService.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@RestController
@RequestMapping("api/v1/movie")

@Tag(
        name = "Movie Controller",
        description = "CRUD REST APIs for managing movies")
public class MovieController {

    private final MovieService movieService;
    private final GenreService genreService;


    @GetMapping
    @Operation(
            summary = "Get all movies",
            description = "Retrieve a list of all movies"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie List", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))}),
            @ApiResponse(responseCode = "404", description = "HTTP Status Internal Server Error", content = @Content)
    })
    public List<Movie> findAllMovies() {
        return movieService.findAllMovies();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a single movie using id",
            description = "Retrieve a single movie using an ID passed as a variable"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "HTTP Status Internal Server Error", content = @Content)
    })
    public Optional<Movie> findMovieById(
            @Parameter(description = "id of movie to be searched") @PathVariable(value = "id") Long id) {
        return movieService.findMovieById(id);
    }

    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @PutMapping
    @Operation(
            summary = "Update a Movie",
            description = "REST API to update a Movie based using RequestBody"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "404", description = "HTTP Status Internal Server Error")
    })
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a Movie",
            description = "REST API to delete a Movie using an id passed as a variable"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "404", description = "HTTP Status Internal Server Error")
    })
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

    @PutMapping("/{movieID}/removeGenre/{genreID}")
    public Movie removeGenreFromMovie(@PathVariable("movieID") Long movieID, @PathVariable("genreID") Long genreID) {
        Genre genre = genreService.findGenreById(genreID).get();
        Movie movie = movieService.findMovieById(movieID).get();

        movie.removeGenre(genre);
        return movieService.saveMovie(movie);
    }
}
