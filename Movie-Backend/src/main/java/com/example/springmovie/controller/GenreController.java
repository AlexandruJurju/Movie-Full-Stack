package com.example.springmovie.controller;

import com.example.springmovie.exception.GenreNotFoundException;
import com.example.springmovie.model.Genre;
import com.example.springmovie.service.interfaces.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@Tag(name="Genre")

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    @Operation(summary = "Find all genres")
    public ResponseEntity<List<Genre>> getAllGenres() {
        return new ResponseEntity<>(genreService.findAllGenres(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a genre using Id")
    public ResponseEntity<Genre> findGenreById(@PathVariable("id") Long id) throws GenreNotFoundException {
        return new ResponseEntity<>(genreService.findGenreById(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Save genre to database")
    public ResponseEntity<Genre> saveGenre(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreService.saveGenre(genre), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update genre")
    public ResponseEntity<Genre> updateGenre(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreService.saveGenre(genre), HttpStatus.OK);
    }

    @DeleteMapping("/{genreId}")
    @Operation(summary = "Delete genre using ID")
    public ResponseEntity<?> deleteGenreById(@PathVariable("genreId") Long genreId) throws GenreNotFoundException {
        genreService.deleteGenre(genreId);
        return ResponseEntity.noContent().build();
    }

}
