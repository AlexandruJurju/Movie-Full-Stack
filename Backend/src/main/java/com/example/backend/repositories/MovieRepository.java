package com.example.backend.repositories;

import com.example.backend.enums.ReleaseStatus;
import com.example.backend.model.Genre;
import com.example.backend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMovieByReleaseStatus(ReleaseStatus status);

    // TODO: using YEAR() will make it so mysql can't use indexers
    @Query("SELECT m FROM Movie m WHERE YEAR(m.releaseDate) = :year")
    List<Movie> findMoviesByYear(@Param("year") int year);

    @Query("SELECT m.genres FROM Movie m WHERE m.id = :movieId")
    List<Genre> findGenresByMovieId(@Param("movieId") Long movieId);

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.id = :genreId")
    List<Movie> findMoviesByGenreId(@Param("genreId") Long genreId);

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.name =:genreName")
    List<Movie> findMoviesByGenreName(@Param("genreName") String genreName);

    // TODO: find all movies that contain keyword
}
