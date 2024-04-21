package com.example.springmovie.service.interfaces;

import com.example.springmovie.exception.ActorNotFoundException;
import com.example.springmovie.model.Actor;

import java.util.List;

public interface ActorService {

    List<Actor> findAllActors();

    Actor findActorById(Long id) throws ActorNotFoundException;

    Actor saveActor(Actor actor);

    void deleteActorById(Long id) throws ActorNotFoundException;
}
