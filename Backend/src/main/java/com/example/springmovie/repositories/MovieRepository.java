package com.example.springmovie.repositories;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMovieByReleaseStatus(ReleaseStatus status);

    @Query("SELECT m FROM Movie m WHERE YEAR(m.releaseDate) = :year")
    List<Movie> findMoviesByYear(@Param("year") int year);

    //    @Query("SELECT m FROM Movie m WHERE EXTRACT(YEAR FROM m.releaseDate) = :year")
    //    List<Movie> findMoviesByYear(@Param("year") int year);

    @Query("SELECT m.genres FROM Movie m WHERE m.id = :movieId")
    List<Genre> findGenresByMovieId(@Param("movieId") Long movieId);

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.id = :genreId")
    List<Movie> findMoviesByGenreId(@Param("genreId") Long genreId);

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.name =:genreName")
    List<Movie> findMoviesByGenreName(@Param("genreName") String genreName);

    @Query("SELECT m FROM Movie m WHERE m.title LIKE CONCAT('%', :keyword, '%')")
    List<Movie> findAllMoviesContainingKeyword(@Param("keyword") String keyword);
}
