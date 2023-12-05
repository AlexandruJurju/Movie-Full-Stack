package com.example.backend.controllers;

import com.example.backend.model.Genre;
import com.example.backend.services.genreService.GenreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@RestController
@RequestMapping("api/v1/genre")

@Tag(name = "Genre Controller", description = "CRUD REST APIs for managing movie genres")

public class GenreController {

    private final GenreService service;

    @GetMapping
    public List<Genre> getAllGenres() {
        return service.findAllGenres();
    }

    @GetMapping("/{id}")
    public Optional<Genre> findGenreById(@PathVariable("id") Long id) {
        return service.findGenreById(id);
    }

    @PostMapping
    public Genre saveGenre(@RequestBody Genre genre) {
        return service.saveGenre(genre);
    }

    @PutMapping
    public Genre updateGenre(@RequestBody Genre genre) {
        return service.saveGenre(genre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenreById(@PathVariable("id") Long id) {
        service.deleteGenre(id);
    }

}
