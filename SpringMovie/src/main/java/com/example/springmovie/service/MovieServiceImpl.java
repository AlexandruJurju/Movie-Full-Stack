package com.example.springmovie.service;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.exception.NotFoundException;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import com.example.springmovie.repositories.MovieRepository;
import com.example.springmovie.service.interfaces.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final S3FileService s3FileService;

    // TODO: add filtering

    @Override
    public Page<Movie> findAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public Movie findMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        Optional<Movie> existingMovie = movieRepository.findById(movie.getId());
        if (existingMovie.isEmpty()) {
            throw new NotFoundException(movie.getId());
        }
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovieById(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> findMoviesByGenre(Long genreId) {
        return movieRepository.findMoviesByGenreId(genreId);
    }

    @Override
    public List<Movie> findMoviesByReleaseStatus(ReleaseStatus status) {
        return movieRepository.findMovieByReleaseStatus(status);
    }

    @Override
    public List<Movie> findMoviesByYear(int year) {
        return movieRepository.findMoviesByYear(year);
    }

    @Override
    public List<Genre> findAllGenresOfMovie(Long movieId) {
        return movieRepository.findGenresByMovieId(movieId);
    }

    @Override
    public Movie saveMovieWithPoster(Movie movie, MultipartFile file) {
        if (file != null) {
            String key = s3FileService.upload(file);
            movie.setPosterUrl(key);
        }
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMoviePoster(Long movieId, MultipartFile file) throws MovieNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(movieId));
        String moviePosterURL = movie.getPosterUrl();
        if (moviePosterURL != null) {
            s3FileService.delete(moviePosterURL);
        }
        if (file != null) {
            movie.setPosterUrl(s3FileService.upload(file));
        }
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMoviePoster(Long movieId) throws MovieNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(movieId));
        String moviePosterURL = movie.getPosterUrl();
        if (moviePosterURL != null) {
            s3FileService.delete(moviePosterURL);
            movie.setPosterUrl(null);
            movieRepository.save(movie);
        }
    }

    @Override
    public byte[] getMoviePoster(Long movieId) throws MovieNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(movieId));
        String moviePosterURL = movie.getPosterUrl();
        if (moviePosterURL != null) {
            return s3FileService.download(moviePosterURL);
        } else {
            return null;
        }
    }

}
