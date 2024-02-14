package com.example.springmovie.service;

import com.example.springmovie.exception.NotFoundException;
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
    public Actor findActorById(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public void deleteActorById(Long id) {
        actorRepository.deleteById(id);
    }
}
