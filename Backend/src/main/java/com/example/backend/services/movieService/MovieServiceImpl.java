package com.example.backend.services.movieService;

import com.example.backend.model.Movie;
import com.example.backend.model.ReleaseStatus;
import com.example.backend.repositories.MovieRepository;
import com.example.backend.services.fileService.LocalFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final LocalFileService localFileService;

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findMovieById(Long id) {
        return movieRepository.findById(id);
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

    @Override
    public String uploadPoster(Long movieID, MultipartFile file) {
        Movie movie = findMovieById(movieID).orElseThrow(() -> new IllegalArgumentException("Cannot find video by id - " + movieID));
        String posterURL = localFileService.upload(file);
        movie.setPosterURL(posterURL);
        movieRepository.save(movie);
        return posterURL;
    }
}
