package com.example.springmovie.repositories;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
    List<Movie> findMovieByReleaseStatus(ReleaseStatus status);

    @Query("SELECT m FROM Movie m WHERE YEAR(m.releaseDate) = :year")
    List<Movie> findMoviesByYear(@Param("year") int year);

    @Query("SELECT m.genres FROM Movie m WHERE m.id = :movieId")
    List<Genre> findGenresByMovieId(@Param("movieId") Long movieId);
}
