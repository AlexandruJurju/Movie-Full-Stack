package com.example.backend.controllers;

import com.example.backend.model.Genre;
import com.example.backend.model.Movie;
import com.example.backend.services.GenreService;
import com.example.backend.services.MovieService;
import com.example.backend.utility.enums.ReleaseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("api/v1/movie")

@Tag(name = "Movie Controller", description = "CRUD REST APIs for managing movies")
public class MovieController {

    private final MovieService movieService;
    private final GenreService genreService;


    @GetMapping
    @Operation(summary = "Get all movies", description = "Retrieve a list of all movies")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Movie List", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))}),
//            @ApiResponse(responseCode = "404", description = "HTTP Not Found", content = @Content)
//    })
    public ResponseEntity<List<Movie>> findAllMovies() {
        List<Movie> movies = movieService.findAllMovies();
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "Get a single movie using id", description = "Retrieve a single movie using an ID passed as a variable")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
//            @ApiResponse(responseCode = "404", description = "HTTP Not Found", content = @Content)
//    })
    public ResponseEntity<Movie> findMovieById(
            @Parameter(description = "id of movie to be searched") @PathVariable(value = "movieId") Long movieId
    ) {
        return new ResponseEntity<>(movieService.findMovieById(movieId), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Save a movie", description = "REST API to save a movie based using RequestBody")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
//            @ApiResponse(responseCode = "404", description = "HTTP Not Found", content = @Content)
//    })
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        Movie aux = movieService.saveMovie(movie);
        return new ResponseEntity<>(aux, HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update a Movie", description = "REST API to update a Movie based using RequestBody")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
//            @ApiResponse(responseCode = "404", description = "HTTP Not Found")
//    })
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        Movie aux = movieService.saveMovie(movie);
        return new ResponseEntity<>(aux, HttpStatus.OK);
    }

    @DeleteMapping("/{movieId}")
    @Operation(summary = "Delete a Movie", description = "REST API to delete a Movie using an id passed as a variable")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
//            @ApiResponse(responseCode = "404", description = "HTTP Not Found")
//    })
    public void deleteMovie(@PathVariable("movieId") Long movieId) {
        movieService.deleteMovieById(movieId);
    }

    @GetMapping("status/{release_status}")
    @Operation(summary = "Find movies by release status")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "HTTP Status OK")
//    })
    public ResponseEntity<List<Movie>> findMoviesByReleaseStatus(@PathVariable("release_status") ReleaseStatus status) {
        List<Movie> movies = movieService.findMovieByReleaseStatus(status);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PutMapping("/{movieId}/addGenre/{genreId}")
    @Operation(summary = "Add a genre to a movie")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
//            @ApiResponse(responseCode = "404", description = "HTTP Not Found")
//    })
    public ResponseEntity<Movie> addGenreToMovie(
            @Parameter(description = "id of movie that the genre will be added to") @PathVariable("movieId") Long movieId,
            @Parameter(description = "id of the genre that will be added to the movie") @PathVariable("genreId") Long genreId) {
        Genre genre = genreService.findGenreById(genreId);
        Movie movie = movieService.findMovieById(movieId);
        movie.addGenre(genre);
        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.OK);
    }

    @PutMapping("/{movieId}/removeGenre/{genreId}")
    @Operation(summary = "Remove a genre from a movie")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
//            @ApiResponse(responseCode = "404", description = "HTTP Not Found")
//    })
    public ResponseEntity<Movie> removeGenreFromMovie(@PathVariable("movieId") Long movieId, @PathVariable("genreId") Long genreId) {
        Genre genre = genreService.findGenreById(genreId);
        if (genre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Movie movie = movieService.findMovieById(movieId);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        movie.removeGenre(genre);
        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.OK);
    }

    @PutMapping(value = "/{movieId}/poster/upload", consumes = {"multipart/form-data"})
    public String uploadPoster(@PathVariable("movieId") Long movieId, @RequestParam("file") MultipartFile file) {
        try {
            return movieService.uploadPoster(movieId, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{movieId}/poster/delete")
    public void deletePoster(@PathVariable("movieId") Long movieId) {
        try {
            movieService.deletePoster(movieId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{movieId}/poster")
    public ResponseEntity<byte[]> getMoviePoster(@PathVariable("movieId") Long movieId) {
        try {
            byte[] image = movieService.downloadPoster(movieId);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Movie>> getMoviesInYear(@PathVariable("year") int year) {
        return new ResponseEntity<>(movieService.findMoviesByYear(year), HttpStatus.OK);
    }

}
