package com.example.springmovie.service;

import com.example.springmovie.model.MovieActor;

import java.util.List;
import java.util.Optional;

public interface MovieActorService {
    MovieActor save(MovieActor movieActor);

    Optional<MovieActor> findById(Long id);

    List<MovieActor> findAll();

    void deleteById(Long id);

    List<MovieActor> findAllByMovieId(Long movieId);
}
