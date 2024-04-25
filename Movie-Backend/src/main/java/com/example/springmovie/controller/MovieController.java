package com.example.springmovie.controller;

import com.example.springmovie.dto.GenreDto;
import com.example.springmovie.dto.MovieDto;
import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.service.interfaces.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Tag(name = "Movie")

@RestController
@RequestMapping("/api/v1/movie")

public class MovieController {


    private final MovieService movieService;

    @GetMapping("/{movieId}")
    @Operation(summary = "Find a single movie using id", description = "Retrieve a single movie using an ID passed as a variable")
    public ResponseEntity<MovieDto> findMovieById(@PathVariable Long movieId) throws MovieNotFoundException {
        Optional<MovieDto> movieDto = movieService.findMovieById(movieId);
        return movieDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("status/{releaseStatus}")
    @Operation(summary = "Find movies by release status")
    public ResponseEntity<List<MovieDto>> findMoviesByReleaseStatus(@PathVariable("releaseStatus") ReleaseStatus status) {
        return ResponseEntity.ok().body(movieService.findMoviesByReleaseStatus(status));
    }

    @GetMapping("/year/{year}")
    @Operation(summary = "Get all movies that were released in a year")
    public ResponseEntity<List<MovieDto>> findMoviesByReleaseYear(@PathVariable int year) {
        return ResponseEntity.ok().body(movieService.findMoviesByYear(year));
    }

    @GetMapping("/unpaged")
    public ResponseEntity<List<MovieDto>> findAllMovies() {
        return ResponseEntity.ok(movieService.findAllMoviesPaged());
    }

    @PostMapping
    @Operation(summary = "Save a movie")
    public ResponseEntity<MovieDto> saveMovie(@RequestBody @Valid MovieDto movieDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.saveMovie(movieDto));
    }

    @PutMapping
    @Operation(summary = "Update a Movie", description = "REST API to update a Movie based using RequestBody")
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.saveMovie(movieDto));
    }

    @DeleteMapping("/{movieId}")
    @Operation(summary = "Delete a Movie", description = "REST API to delete a Movie using an id passed as a variable")
    public ResponseEntity<Void> deleteMovieById(@PathVariable("movieId") Long movieId) throws MovieNotFoundException {
        movieService.deleteMovieById(movieId);
        return ResponseEntity.noContent().build();
    }

    // ========================== Genre Operations ==========================

    @PutMapping("/{movieId}/genres/{genreId}")
    @Operation(summary = "Add a genre to a movie")
    public ResponseEntity<MovieDto> addGenreToMovie(@Parameter(description = "ID of movie that the genre will be added to") @PathVariable("movieId") Long movieId,
                                                    @Parameter(description = "ID of the genre that will be added to the movie") @PathVariable("genreId") Long genreId) {
        Optional<MovieDto> movieDto = movieService.addGenreToMovie(movieId, genreId);
        return movieDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{movieId}/genres/{genreId}")
    @Operation(summary = "Remove a genre from a movie")
    public ResponseEntity<MovieDto> removeGenreFromMovie(@PathVariable("movieId") Long movieId, @PathVariable("genreId") Long genreId) {
        Optional<MovieDto> movieDto = movieService.removeGenreFromMovie(movieId, genreId);
        return movieDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{movieId}/genres")
    @Operation(summary = "Find all genres of a movie")
    public ResponseEntity<List<GenreDto>> findGenresOfMovie(@PathVariable("movieId") Long movieId) {
        return ResponseEntity.ok(movieService.findAllGenresOfMovie(movieId));
    }

    //    @GetMapping("movie/findByGenreId/{genreId}")
    //    @Operation(summary = "Find all movies that contain a genre using the genreId")
    //    public ResponseEntity<List<MovieDto>> findAllMoviesContainingGenre(@PathVariable("genreId") Long genreId) {
    //        return ResponseEntity.ok(movieService.findMoviesByGenreId(genreId));
    //    }

    // ========================== Poster Operations ==========================

    //    @PostMapping(value = "/saveWithPoster", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //    @Operation(summary = "Save a movie together with its poster")
    //    public ResponseEntity<MovieDto> saveMovieWithPoster(Movie movie, @RequestParam(name = "file", required = false) MultipartFile file) {
    //        return new ResponseEntity<>(movieService.saveMovieWithPoster(movie, file), HttpStatus.CREATED);
    //    }
    //
    //    @PostMapping(value = "/poster", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //    @Operation(summary = "Update the poster of a movie")
    //    public ResponseEntity<MovieDto> updateMoviePoster(@RequestParam("movieId") Long movieId, @RequestParam(value = "file") MultipartFile file) throws
    //    MovieNotFoundException {
    //        movieService.deleteMoviePoster(movieId);
    //        return ResponseEntity.ok(movieService.updateMoviePoster(movieId, file));
    //    }
    //
    //    @PutMapping("/{movieId}/poster/delete")
    //    @Operation(summary = "Delete a poster from a movie")
    //    public ResponseEntity<Object> deletePoster(@PathVariable("movieId") Long movieId) throws MovieNotFoundException {
    //        movieService.deleteMoviePoster(movieId);
    //        return ResponseEntity.noContent().build();
    //    }
    //
    //    @GetMapping("/{movieId}/poster")
    //    @Operation(summary = "Get the poster image from a movie")
    //    public ResponseEntity<byte[]> getMoviePoster(@PathVariable("movieId") Long movieId) throws MovieNotFoundException {
    //        byte[] image = movieService.getMoviePoster(movieId);
    //        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    //    }

}
