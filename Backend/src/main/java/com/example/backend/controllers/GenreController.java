package com.example.backend.controllers;

import com.example.backend.model.Genre;
import com.example.backend.model.Movie;
import com.example.backend.services.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/genre")

@Tag(name = "Genre Controller", description = "CRUD REST APIs for managing movie genres")

public class GenreController {

    private final GenreService service;

    @GetMapping
    @Operation(summary = "Find all genres")
    public List<Genre> getAllGenres() {
        return service.findAllGenres();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a genre using Id")
    public ResponseEntity<Genre> findGenreById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.findGenreById(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Save genre to database")
    public Genre saveGenre(@RequestBody Genre genre) {
        return service.saveGenre(genre);
    }

    @PutMapping
    @Operation(summary = "Update genre")
    public Genre updateGenre(@RequestBody Genre genre) {
        return service.saveGenre(genre);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete genre using id")
    public void deleteGenreById(@PathVariable("id") Long id) {
        service.deleteGenre(id);
    }

}
