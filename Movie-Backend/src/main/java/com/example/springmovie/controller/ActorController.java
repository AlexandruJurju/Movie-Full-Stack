package com.example.springmovie.controller;

import com.example.springmovie.dto.ActorDto;
import com.example.springmovie.exception.ActorNotFoundException;
import com.example.springmovie.service.interfaces.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor

@Tag(name = "Actor")

@RestController
@RequestMapping("/api/v1/actor")
public class ActorController {
    private final ActorService actorService;

    @GetMapping("")
    @Operation(summary = "Find all actors")
    public ResponseEntity<List<ActorDto>> findAllActors() {
        return ResponseEntity.ok(actorService.findAllActors());
    }

    @GetMapping("/{actorId}")
    @Operation(summary = "Find an actor using Id")
    public ResponseEntity<ActorDto> findActorById(@PathVariable("actorId") Long actorId) throws ActorNotFoundException {
        Optional<ActorDto> actorDto = actorService.findActorById(actorId);
        return actorDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(summary = "Add an actor to database")
    public ResponseEntity<ActorDto> saveActor(@RequestBody ActorDto actor) {
        return new ResponseEntity<>(actorService.saveActor(actor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{actorId}")
    @Operation(summary = "Delete actor given id")
    public ResponseEntity<Void> deleteActor(@PathVariable("actorId") Long actorId) throws ActorNotFoundException {
        actorService.deleteActorById(actorId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("")
    @Operation(summary = "Update an actor")
    public ResponseEntity<ActorDto> updateActor(@RequestBody ActorDto actor) {
        return ResponseEntity.ok(actorService.saveActor(actor));
    }
}
