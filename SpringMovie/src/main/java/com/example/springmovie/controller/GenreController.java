package com.example.springmovie.controller;

import com.example.springmovie.model.Genre;
import com.example.springmovie.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
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
