package com.example.springmovie.controller;

import com.example.springmovie.model.Actor;
import com.example.springmovie.service.interfaces.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Actor Controller", description = "CRUD REST APIs for actors in movies")

@RequiredArgsConstructor

@RestController
@RequestMapping("/actor")
public class ActorController {

    private final ActorService actorService;

    @GetMapping("")
    @Operation(summary = "Find all actors")
    public List<Actor> findAllActors() {
        return actorService.findAllActors();
    }

    @PostMapping("")
    @Operation(summary = "Add an actor to database")
    public Actor saveActor(@RequestBody Actor actor) {
        return actorService.saveActor(actor);
    }

    @DeleteMapping("/{actorId}")
    @Operation(summary = "Delete actor given id")
    public void deleteActor(@PathVariable("actorId") Long actorId) {
        actorService.deleteActorById(actorId);
    }

    @PutMapping("")
    @Operation(summary = "Update an actor")
    public Actor updateActor(@RequestBody Actor actor) {
        return actorService.saveActor(actor);
    }
}
