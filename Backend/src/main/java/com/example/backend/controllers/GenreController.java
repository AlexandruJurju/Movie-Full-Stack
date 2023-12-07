package com.example.backend.controllers;

import com.example.backend.model.Genre;
import com.example.backend.model.Movie;
import com.example.backend.services.genreService.GenreService;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))}),
            @ApiResponse(responseCode = "400", description = "HTTP Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "HTTP Not Found", content = @Content)
    })
    public ResponseEntity<Genre> findGenreById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.findGenreById(id), HttpStatus.OK);
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
