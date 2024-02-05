package com.example.springmovie.service.impl;

import com.example.springmovie.model.Actor;
import com.example.springmovie.repositories.ActorRepository;
import com.example.springmovie.service.ActorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Actor> findAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findActorById(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find actor with id " + id));
    }

    @Override
    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }
}
