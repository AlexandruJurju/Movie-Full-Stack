package com.example.springmovie.controller;

import com.example.springmovie.dto.MovieActorDto;
import com.example.springmovie.exception.ActorNotFoundException;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.model.Actor;
import com.example.springmovie.model.Movie;
import com.example.springmovie.model.MovieActor;
import com.example.springmovie.service.interfaces.ActorService;
import com.example.springmovie.service.interfaces.MovieActorService;
import com.example.springmovie.service.interfaces.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@Tag(name = "MovieActor Controller", description = "Manages the many-to-many relation between movie and actors")

@RestController
@RequestMapping("/api/v1/movie-actor")
public class MovieActorController {

    private final MovieActorService movieActorService;
    private final MovieService movieService;
    private final ActorService actorService;

    // TODO: put in service ?
    @PostMapping("/add")
    @Operation(summary = "Add an actor to a a movie")
    public ResponseEntity<MovieActor> addActorToMovie(@RequestBody MovieActorDto movieActorDto) throws MovieNotFoundException, ActorNotFoundException {
        Movie movie = movieService.findMovieById(movieActorDto.getMovieId());
        Actor actor = actorService.findActorById(movieActorDto.getActorId());

        MovieActor movieActor = new MovieActor();
        movieActor.setActor(actor);
        movieActor.setMovie(movie);
        movieActor.setRole(movieActorDto.getRole());
        movieActor.setDisplayOrder(movieActorDto.getDisplayOrder());
        movieActor.setCharacterImageUrl(movieActorDto.getCharacterImageUrl());

        return new ResponseEntity<>(movieActorService.saveMovieActor(movieActor), HttpStatus.CREATED);
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
