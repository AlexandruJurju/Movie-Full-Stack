package com.example.springmovie.repositories;

import com.example.springmovie.model.MovieActor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieActorRepository extends JpaRepository<MovieActor, Long> {
    List<MovieActor> findByMovieId(Long movieId);

    List<MovieActor> findByMovieIdAndActorId(Long movieId, Long actorId);

}
