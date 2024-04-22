package com.example.springmovie.controller;

import com.example.springmovie.exception.ActorNotFoundException;
import com.example.springmovie.model.Actor;
import com.example.springmovie.service.interfaces.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor

@Tag(name="Actor")

@RestController
@RequestMapping("/api/v1/actor")
public class ActorController {

    // TODO: actor save with image
    // TODO: upload, update actor image
    // TODO: search actors by name

    private final ActorService actorService;

    @GetMapping("")
    @Operation(summary = "Find all actors")
    public ResponseEntity<List<Actor>> findAllActors() {
        return ResponseEntity.ok(actorService.findAllActors());
    }

    @GetMapping("/{actorId}")
    @Operation(summary = "Find an actor using Id")
    public ResponseEntity<Actor> findActorById(@PathVariable("actorId") Long actorId) throws ActorNotFoundException {
        return new ResponseEntity<>(actorService.findActorById(actorId), HttpStatus.OK);
    }

    @PostMapping("")
    @Operation(summary = "Add an actor to database")
    public ResponseEntity<Actor> saveActor(@RequestBody Actor actor) {
        return new ResponseEntity<>(actorService.saveActor(actor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{actorId}")
    @Operation(summary = "Delete actor given id")
    public ResponseEntity<?> deleteActor(@PathVariable("actorId") Long actorId) throws ActorNotFoundException {
        actorService.deleteActorById(actorId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("")
    @Operation(summary = "Update an actor")
    public ResponseEntity<Actor> updateActor(@RequestBody Actor actor) {
        return ResponseEntity.ok(actorService.saveActor(actor));
    }
}
