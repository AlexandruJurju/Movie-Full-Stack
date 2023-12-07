package com.example.backend.services.movieService;

import com.example.backend.model.Movie;
import com.example.backend.enums.ReleaseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {

    List<Movie> findAllMovies();

    Movie findMovieById(Long id);

    Movie saveMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovieById(Long id);

    List<Movie> findMovieByReleaseStatus(ReleaseStatus status);

    String uploadPoster(Long movieID, MultipartFile file);

    public List<Movie> findMoviesByYear(int year);
}
