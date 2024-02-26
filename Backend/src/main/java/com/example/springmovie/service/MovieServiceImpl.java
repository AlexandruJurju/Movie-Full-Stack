package com.example.springmovie.service;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import com.example.springmovie.repositories.MovieRepository;
import com.example.springmovie.service.interfaces.MovieService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public List<Movie> findAllMovies(){
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> filterMovies(Integer startReleaseYear, Integer endReleaseYear, Set<Genre> genres, String title) {
        return movieRepository.findAll((Specification<Movie>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (startReleaseYear != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.function("YEAR", Integer.class, root.get("releaseDate")), startReleaseYear));
            }

            if (endReleaseYear != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.function("YEAR", Integer.class, root.get("releaseDate")), endReleaseYear));
            }

            if (genres != null && !genres.isEmpty()) {
                CriteriaBuilder.In<Genre> genrePredicate = criteriaBuilder.in(root.get("genres"));
                genres.forEach(genrePredicate::value);
                predicates.add(genrePredicate);
            }

            if (title != null) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }


    @Override
    public Movie findMovieById(Long id) throws MovieNotFoundException {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + id + " not found"));
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) throws MovieNotFoundException {
        Optional<Movie> existingMovie = movieRepository.findById(movie.getId());
        if (existingMovie.isEmpty()) {
            throw new MovieNotFoundException("Movie with id " + movie.getId() + " not found");
        }
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovieById(Long id) throws MovieNotFoundException {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException("Movie with id " + id + " not found");
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
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + movieId + " not found"));
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
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + movieId + " not found"));
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
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + movieId + " not found"));
        String moviePosterURL = movie.getPosterUrl();
        if (moviePosterURL != null) {
            return s3FileService.download(moviePosterURL);
        } else {
            return null;
        }
    }

}
