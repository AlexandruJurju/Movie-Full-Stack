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

@Tag(name = "Actor Controller", description = "CRUD REST APIs for actors in movies")

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/actor")
public class ActorController {

    private final ActorService actorService;

    @GetMapping("")
    @Operation(summary = "Find all actors")
    public ResponseEntity<List<Actor>> findAllActors() {
        List<Actor> actors = actorService.findAllActors();
        return ResponseEntity.ok(actors);
    }

    @PostMapping("")
    @Operation(summary = "Add an actor to database")
    public ResponseEntity<Actor> saveActor(@RequestBody Actor actor) {
        Actor savedActor = actorService.saveActor(actor);
        return new ResponseEntity<>(savedActor, HttpStatus.CREATED);
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
        Actor updatedActor = actorService.saveActor(actor);
        return ResponseEntity.ok(updatedActor);
    }
}
