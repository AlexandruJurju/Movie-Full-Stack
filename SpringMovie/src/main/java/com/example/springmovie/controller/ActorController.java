package com.example.springmovie.controller;

import com.example.springmovie.dto.MovieActorDto;
import com.example.springmovie.model.Actor;
import com.example.springmovie.model.Movie;
import com.example.springmovie.model.MovieActor;
import com.example.springmovie.service.interfaces.ActorService;
import com.example.springmovie.service.interfaces.MovieActorService;
import com.example.springmovie.service.interfaces.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Actor Controller", description = "CRUD REST APIs for actors in movies")

@RequiredArgsConstructor

@RestController
@RequestMapping("/actor")
public class ActorController {

    private final ActorService actorService;
    private final MovieService movieService;
    private final MovieActorService movieActorService;

    @GetMapping("")
    @Operation(summary = "Find all actors")
    public List<Actor> findAllActors() {
        return actorService.findAllActors();
    }

    @PostMapping("")
    @Operation(summary = "Add an actor to database")
    public Actor saveActor(@RequestBody Actor actor) {
        return actorService.saveActor(actor);
    }

    @DeleteMapping("/{actorId}")
    @Operation(summary = "Delete actor given id")
    public void deleteActor(@PathVariable("actorId") Long actorId) {
        actorService.deleteActorById(actorId);
    }

    @PutMapping("")
    @Operation(summary = "Update an actor")
    public Actor updateActor(@RequestBody Actor actor) {
        return actorService.saveActor(actor);
    }

    @PostMapping("/add")
    @Operation(summary = "Add an actor to a a movie")
    public MovieActor addActorToMovie(@RequestBody MovieActorDto movieActorDto) {
        Movie movie = movieService.findMovieById(movieActorDto.movieId());
        Actor actor = actorService.findActorById(movieActorDto.actorId());

        MovieActor movieActor = new MovieActor();
        movieActor.setActor(actor);
        movieActor.setMovie(movie);
        movieActor.setRole(movieActorDto.role());
        movieActor.setDisplayOrder(movieActorDto.displayOrder());
        movieActor.setCharacterImageUrl(movieActorDto.characterImageUrl());

        return movieActorService.save(movieActor);
    }

    @DeleteMapping("/{movieId}/{actorId}")
    @Operation(summary = "Remove actor from movie")
    public void removeActorFromMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        List<MovieActor> movieCasts = movieActorService.findByMovieIdAndActorId(movieId, actorId);

        for (MovieActor movieCast : movieCasts) {
            movieActorService.deleteById(movieCast.getId());
        }
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "Get all actors from a movie")
    public List<MovieActorDto> getActorsByMovieId(@PathVariable Long movieId) {
        List<MovieActor> movieActors = movieActorService.findAllByMovieId(movieId);

        return movieActors.stream()
                .map(movieActor -> new MovieActorDto(
                        movieId,
                        movieActor.getActor().getId(),
                        movieActor.getRole(),
                        movieActor.getDisplayOrder(),
                        movieActor.getCharacterImageUrl()
                ))
                .collect(Collectors.toList());
    }


}
