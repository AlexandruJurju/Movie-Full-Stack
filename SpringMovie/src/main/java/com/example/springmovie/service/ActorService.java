package com.example.springmovie.service;

import com.example.springmovie.model.Actor;

import java.util.List;

public interface ActorService {

    List<Actor> findAllActors();

    Actor findActorById(Long id);

    Actor saveActor(Actor actor);

    void deleteActor(Long id);
}
