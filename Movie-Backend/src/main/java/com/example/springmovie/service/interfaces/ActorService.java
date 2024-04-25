package com.example.springmovie.service.interfaces;

import com.example.springmovie.dto.ActorDto;
import com.example.springmovie.exception.ActorNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    List<ActorDto> findAllActors();

    Optional<ActorDto> findActorById(Long id) throws ActorNotFoundException;

    ActorDto saveActor(ActorDto actorDto);

    void deleteActorById(Long id) throws ActorNotFoundException;
}
