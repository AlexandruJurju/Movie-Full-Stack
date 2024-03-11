package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.MovieActorDto;
import com.example.springmovie.exception.ActorNotFoundException;
import com.example.springmovie.exception.MovieActorNotFoundException;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.model.MovieActor;

import java.util.List;

public interface MovieActorService {
    MovieActor addActorToMovie(MovieActorDto movieActorDto) throws MovieNotFoundException, ActorNotFoundException;

    MovieActor saveMovieActor(MovieActor movieActor);

    MovieActor findMovieActorById(Long id) throws MovieActorNotFoundException;

    List<MovieActor> findAll();

    void deleteById(Long id);

    List<MovieActor> findAllByMovieId(Long movieId);

    List<MovieActor> findByMovieIdAndActorId(Long movieId, Long actorId);
}
