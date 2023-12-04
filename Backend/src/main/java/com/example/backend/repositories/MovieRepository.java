package com.example.backend.repositories;

import com.example.backend.model.Movie;
import com.example.backend.model.ReleaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMovieByReleaseStatus(ReleaseStatus status);
}
