package com.example.springmovie.repositories;

import com.example.springmovie.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    @Query("SELECT a FROM Actor a WHERE a.firstName LIKE CONCAT('%', :name, '%')")
    List<Actor> findByNameContaining(@Param("name") String name);

}
