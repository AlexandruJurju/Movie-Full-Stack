package com.example.springmovie.service;

import com.example.springmovie.exception.ActorNotFoundException;
import com.example.springmovie.model.Actor;
import com.example.springmovie.repositories.ActorRepository;
import com.example.springmovie.service.interfaces.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Override
    public List<Actor> findAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findActorById(Long id) throws ActorNotFoundException {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException("Actor with id " + id + " not found"));
    }

    @Override
    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public void deleteActorById(Long id) throws ActorNotFoundException {
        actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException("Actor with id " + id + " not found"));
        actorRepository.deleteById(id);
    }
}
