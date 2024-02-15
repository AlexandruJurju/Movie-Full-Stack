package com.example.springmovie.service.interfaces;

import com.example.springmovie.model.MovieActor;

import java.util.List;

public interface MovieActorService {
    MovieActor saveMovieActor(MovieActor movieActor);

    MovieActor findMovieActorById(Long id);

    List<MovieActor> findAll();

    void deleteById(Long id);

    List<MovieActor> findAllByMovieId(Long movieId);

    List<MovieActor> findByMovieIdAndActorId(Long movieId, Long actorId);
}
