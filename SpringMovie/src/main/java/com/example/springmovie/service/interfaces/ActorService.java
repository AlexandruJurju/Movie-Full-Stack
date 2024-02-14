package com.example.springmovie.service.interfaces;

import com.example.springmovie.model.Actor;

import java.util.List;

public interface ActorService {

    List<Actor> findAllActors();

    Actor findActorById(Long id);

    Actor saveActor(Actor actor);

    void deleteActorById(Long id);
}
