package com.example.backend.services.movieService;

import com.example.backend.exception.MovieInvalidIdException;
import com.example.backend.model.Movie;
import com.example.backend.enums.ReleaseStatus;
import com.example.backend.repositories.MovieRepository;
import com.example.backend.services.fileService.LocalFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieInvalidIdException("Cannot find movie with id - " + id));
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> findMovieByReleaseStatus(ReleaseStatus status) {
        return movieRepository.findMovieByReleaseStatus(status);
    }

    public List<Movie> findMoviesByYear(int year) {
        return movieRepository.findMoviesByYear(year);
    }

    @Override
    public String uploadPoster(Long movieID, MultipartFile file) {
        Movie movie = findMovieById(movieID);
    }
}
