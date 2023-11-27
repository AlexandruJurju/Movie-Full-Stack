package com.example.backend.controllers;

import com.example.backend.models.Genre;
import com.example.backend.services.genreService.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/genre")
public class GenreController {

    private final GenreService service;

    public GenreController(GenreService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Genre> getAllGenres() {
        return service.findAllGenres();
    }

    @GetMapping("/{id}")
    public Optional<Genre> findGenreById(@PathVariable("id") Long id) {
        return service.findGenreById(id);
    }

    @PostMapping("")
    public Genre saveGenre(Genre genre) {
        return service.saveGenre(genre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenreById(Long id) {
        service.deleteGenre(id);
    }

}
