package com.example.springmovie.repositories;

import com.example.springmovie.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    // TODO: find actor by name
    // TODO: find actor by age
}
