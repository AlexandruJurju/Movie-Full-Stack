package com.example.springmovie.service;

import com.example.springmovie.exception.NotFoundException;
import com.example.springmovie.model.MovieActor;
import com.example.springmovie.repositories.MovieActorRepository;
import com.example.springmovie.service.interfaces.MovieActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieActorImpl implements MovieActorService {

    private final MovieActorRepository movieActorRepository;

    @Override
    public MovieActor saveMovieActor(MovieActor movieActor) {
        return movieActorRepository.save(movieActor);
    }

    @Override
    public MovieActor findMovieActorById(Long id) {
        return movieActorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
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
