package com.example.backend.services.implementation;

import com.example.backend.exception.MovieInvalidIdException;
import com.example.backend.model.Movie;
import com.example.backend.repositories.MovieRepository;
import com.example.backend.services.MovieService;
import com.example.backend.utility.enums.ReleaseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ImageService imageService;

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieInvalidIdException("Cannot find movie with id - " + id));
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

    // todo: upload, download and delete will have to be implemented again for actors, maybe separate image to a different entity
    @Override
    public String uploadPoster(Long movieId, MultipartFile file) throws IOException {
        Movie movie = findMovieById(movieId);
        String posterURL = imageService.uploadImage(file);
        movie.setImageURL(posterURL);
        movieRepository.save(movie);
        return posterURL;
    }

    @Override
    public byte[] downloadPoster(Long movieId) throws IOException {
        Movie movie = findMovieById(movieId);
        log.info(movie.getImageURL());
        return imageService.downloadImage(movie.getImageURL());
    }

    @Override
    public void deletePoster(Long movieId) throws IOException {
        Movie movie = findMovieById(movieId);
        imageService.deleteImage(movie.getImageURL());
    }
}
