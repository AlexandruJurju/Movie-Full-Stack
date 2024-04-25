package com.example.springmovie.service;

import com.example.springmovie.dto.ActorDto;
import com.example.springmovie.exception.ActorNotFoundException;
import com.example.springmovie.mappers.ActorMapper;
import com.example.springmovie.model.Actor;
import com.example.springmovie.repositories.ActorRepository;
import com.example.springmovie.service.interfaces.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    @Override
    public List<ActorDto> findAllActors() {
        List<Actor> actors = actorRepository.findAll();
        return actorMapper.toDto(actors);
    }

    @Override
    public Optional<ActorDto> findActorById(Long id) {
        return actorRepository.findById(id).map(actorMapper::toDto);
    }

    @Override
    public ActorDto saveActor(ActorDto actorDto) {
        Actor actor = actorMapper.toEntity(actorDto);
        return actorMapper.toDto(actorRepository.save(actor));
    }

    @Override
    public void deleteActorById(Long id) throws ActorNotFoundException {
        actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException("Actor with id " + id + " not found"));
        actorRepository.deleteById(id);
    }
}
