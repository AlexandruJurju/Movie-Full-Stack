package com.example.springmovie.controller;

import com.example.springmovie.dto.GenreDto;
import com.example.springmovie.dto.MovieDto;
import com.example.springmovie.exception.GenreNotFoundException;
import com.example.springmovie.service.interfaces.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Tag(name = "Genre")

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    @Operation(summary = "Find all genres")
    public ResponseEntity<List<GenreDto>> getAllGenres() {
        return ResponseEntity.ok(genreService.findAllGenres());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a genre using Id")
    public ResponseEntity<GenreDto> findGenreById(@PathVariable("id") Long id) {
        Optional<GenreDto> genreDtoOptional = genreService.findGenreById(id);
        return genreDtoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Save genre to database")
    public ResponseEntity<GenreDto> saveGenre(@RequestBody GenreDto genre) {
        return new ResponseEntity<>(genreService.saveGenre(genre), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update genre")
    public ResponseEntity<GenreDto> updateGenre(@RequestBody GenreDto genre) {
        return ResponseEntity.ok(genreService.saveGenre(genre));
    }

    @DeleteMapping("/{genreId}")
    @Operation(summary = "Delete genre using ID")
    public ResponseEntity<Void> deleteGenreById(@PathVariable("genreId") Long genreId) throws GenreNotFoundException {
        genreService.deleteGenre(genreId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{genreId}/movies")
    @Operation(summary = "Find all movies with genre")
    public ResponseEntity<List<MovieDto>> findAllMoviesWithGenre(@PathVariable("genreId") Long genreId) {
        return ResponseEntity.ok(genreService.findAllMoviesWithGenre(genreId));
    }

}
