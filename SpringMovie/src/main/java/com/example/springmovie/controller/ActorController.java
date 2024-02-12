package com.example.springmovie.controller;

import com.example.springmovie.model.Actor;
import com.example.springmovie.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor

@RestController
@RequestMapping("/actor")
public class ActorController {

    private final ActorService actorService;

    @GetMapping("")
    public List<Actor> findAllActors() {
        //        if (actors.isEmpty()) {
        //            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //        }
        return actorService.findAll();
    }
}
