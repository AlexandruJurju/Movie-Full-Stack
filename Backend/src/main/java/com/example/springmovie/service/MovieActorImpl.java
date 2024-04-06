package com.example.springmovie.service;

import com.example.springmovie.dto.MovieActorDto;
import com.example.springmovie.exception.ActorNotFoundException;
import com.example.springmovie.exception.MovieActorNotFoundException;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.model.Actor;
import com.example.springmovie.model.Movie;
import com.example.springmovie.model.MovieActor;
import com.example.springmovie.repositories.MovieActorRepository;
import com.example.springmovie.service.interfaces.ActorService;
import com.example.springmovie.service.interfaces.MovieActorService;
import com.example.springmovie.service.interfaces.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieActorImpl implements MovieActorService {

    private final MovieActorRepository movieActorRepository;
    private final MovieService movieService;
    private final ActorService actorService;

    @Override
    public MovieActor addActorToMovie(MovieActorDto movieActorDto) throws MovieNotFoundException, ActorNotFoundException {
        Movie movie = movieService.findMovieById(movieActorDto.movieId());
        Actor actor = actorService.findActorById(movieActorDto.actorId());

        MovieActor movieActor = new MovieActor();
        movieActor.setActor(actor);
        movieActor.setMovie(movie);
        movieActor.setRole(movieActorDto.role());
        movieActor.setDisplayOrder(movieActorDto.displayOrder());
        movieActor.setCharacterImageUrl(movieActorDto.characterImageUrl());

        movieActorRepository.save(movieActor);

        return movieActor;
    }

    @Override
    public MovieActor saveMovieActor(MovieActor movieActor) {
        return movieActorRepository.save(movieActor);
    }

    @Override
    public MovieActor findMovieActorById(Long id) throws MovieActorNotFoundException {
        return movieActorRepository.findById(id)
                .orElseThrow(() -> new MovieActorNotFoundException("Movie-actor with id " + id + " not found"));
    }

    @Override
    public List<MovieActor> findAll() {
        return movieActorRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        movieActorRepository.deleteById(id);
    }

    @Override
    public List<MovieActor> findAllByMovieId(Long movieId) {
        return movieActorRepository.findByMovieId(movieId);
    }

    @Override
    public List<MovieActor> findByMovieIdAndActorId(Long movieId, Long actorId) {
        return movieActorRepository.findByMovieIdAndActorId(movieId, actorId);
    }


}
