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

@RequiredArgsConstructor

@Tag(name = "MovieActor Controller", description = "Manages the many-to-many relation between movie and actors")

@RestController
@RequestMapping("/movie-actor")
public class MovieActorController {

    private final MovieActorService movieActorService;
    private final MovieService movieService;
    private final ActorService actorService;

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

        return movieActorService.saveMovieActor(movieActor);
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

        return movieActors.stream().map(movieActor -> new MovieActorDto(movieId, movieActor.getActor().getId(), movieActor.getRole(), movieActor.getDisplayOrder(),
                movieActor.getCharacterImageUrl())).toList();
    }


}
