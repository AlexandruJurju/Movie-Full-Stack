package com.example.springmovie.controller;

import com.example.springmovie.model.Actor;
import com.example.springmovie.service.interfaces.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Actor Controller", description = "CRUD REST APIs for actors in movies")

@RequiredArgsConstructor
// todo: actor exceptions
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
    @ApiResponse(responseCode = "204", description = "Actor deleted successfully")
    public ResponseEntity<?> deleteActor(@PathVariable("actorId") Long actorId) {
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
