package com.example.springmovie.service;

import com.example.springmovie.model.Actor;

import java.util.List;

public interface ActorService {

    List<Actor> findAll();

    Actor findById(Long id);

    Actor save(Actor actor);

    void delete(Long id);
}
