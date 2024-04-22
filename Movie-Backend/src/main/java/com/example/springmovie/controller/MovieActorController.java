package com.example.springmovie.controller;

import com.example.springmovie.dto.MovieActorDto;
import com.example.springmovie.exception.ActorNotFoundException;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.model.MovieActor;
import com.example.springmovie.service.interfaces.MovieActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@Tag(name="MovieActor")

// TODO: remove, put methods in Movie or Actor
@RestController
@RequestMapping("/api/v1/movie-actor")
public class MovieActorController {

    private final MovieActorService movieActorService;

    @PostMapping("/add")
    @Operation(summary = "Add an actor to a a movie")
    public ResponseEntity<MovieActor> addActorToMovie(@RequestBody MovieActorDto movieActorDto) throws MovieNotFoundException, ActorNotFoundException {
        return new ResponseEntity<>(movieActorService.addActorToMovie(movieActorDto), HttpStatus.CREATED);
    }

    // TODO: rewrite, a movie can have an actor with multiple roles
    @DeleteMapping("/{movieId}/{actorId}")
    @Operation(summary = "Remove actor from movie")
    public ResponseEntity<?> removeActorFromMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        List<MovieActor> movieActors = movieActorService.findByMovieIdAndActorId(movieId, actorId);

        if (movieActors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        for (MovieActor movieCast : movieActors) {
            movieActorService.deleteById(movieCast.getId());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "Get all actors from a movie")
    public ResponseEntity<List<MovieActorDto>> getActorsByMovieId(@PathVariable Long movieId) {
        List<MovieActor> movieActors = movieActorService.findAllByMovieId(movieId);

        if (movieActors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<MovieActorDto> response = movieActors.stream().map
                (movieActor -> new MovieActorDto(movieId, movieActor.getActor().getId(), movieActor.getRole(),
                        movieActor.getDisplayOrder(), movieActor.getCharacterImageUrl())).toList();

        return ResponseEntity.ok(response);
    }


}
