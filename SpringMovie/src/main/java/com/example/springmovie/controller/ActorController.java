package com.example.springmovie.controller;

import com.example.springmovie.model.Actor;
import com.example.springmovie.model.Movie;
import com.example.springmovie.model.MovieActor;
import com.example.springmovie.service.ActorService;
import com.example.springmovie.service.MovieActorService;
import com.example.springmovie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        return actorService.findAll();
    }

    @PostMapping("")
    @Operation(summary = "Add an actor to database")
    public Actor saveActor(@RequestBody Actor actor) {
        return actorService.save(actor);
    }

    @DeleteMapping("/{actorId}")
    @Operation(summary = "Delete actor given id")
    public void deleteActor(@PathVariable("actorId") Long actorId) {
        actorService.delete(actorId);
    }

    @PutMapping("")
    @Operation(summary = "Update an actor")
    public Actor updateActor(@RequestBody Actor actor) {
        return actorService.save(actor);
    }

    @PostMapping("/add")
    @Operation(summary = "Add an actor to a a movie")
    public MovieActor addActorToMovie(@RequestBody MovieActor movieCast) {
        Movie movie = movieService.findById(movieCast.getMovie().getId());
        Actor actor = actorService.findById(movieCast.getActor().getId());

        movieCast.setMovie(movie);
        movieCast.setActor(actor);

        movieActorService.save(movieCast);

        return movieCast;
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
    public List<MovieActor> getActorsByMovieId(@PathVariable Long movieId) {

        return movieActorService.findAllByMovieId(movieId);
    }


}
