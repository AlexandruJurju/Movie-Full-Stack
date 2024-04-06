package com.example.springmovie.service;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.exception.GenreNotFoundException;
import com.example.springmovie.exception.MovieNotFoundException;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import com.example.springmovie.repositories.MovieRepository;
import com.example.springmovie.service.interfaces.GenreService;
import com.example.springmovie.service.interfaces.MovieService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final S3FileService s3FileService;
    private final GenreService genreService;

    @Override
    public Page<Movie> findAllMovies(Pageable pageable) {
        log.info("Find all movies with pagination");
        return movieRepository.findAll(pageable);
    }

    @Override
    public List<Movie> findAllMovies() {
        log.info("Find all movies unpaged");
        return movieRepository.findAll();
    }

    // TODO: finish filtering
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
        log.info("Find movie by id " + id);
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + id + " not found"));
    }

    @Override
    public Movie saveMovie(Movie movie) {
        log.info("Saving movie to the database");

        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) throws MovieNotFoundException {
        log.info("Updating movie in the database");

        Optional<Movie> existingMovie = movieRepository.findById(movie.getId());
        if (existingMovie.isEmpty()) {
            throw new MovieNotFoundException("Movie with id " + movie.getId() + " not found");
        }
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovieById(Long id) throws MovieNotFoundException {
        log.info("Deleting movie by id: " + id);

        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException("Movie with id " + id + " not found");
        }
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> findMoviesByGenreId(Long genreId) {
        log.info("Find movies by genre id " + genreId);
        return movieRepository.findMoviesByGenreId(genreId);
    }

    @Override
    public List<Movie> findMoviesByReleaseStatus(ReleaseStatus status) {
        log.info("Find movies by status " + status);
        return movieRepository.findMovieByReleaseStatus(status);
    }

    @Override
    public List<Movie> findMoviesByYear(int year) {
        log.info("Find movie by year " + year);
        return movieRepository.findMoviesByYear(year);
    }

    @Override
    public List<Genre> findAllGenresOfMovie(Long movieId) {
        log.info("Find all genres of movie " + movieId);
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
        log.info("Updating movie poster for movie id: " + movieId);

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + movieId + " not found"));

        String moviePosterURL = movie.getPosterUrl();
        if (moviePosterURL != null) {
            log.info("Deleting existing movie poster for movie id: " + movieId);
            s3FileService.delete(moviePosterURL);
        }
        if (file != null) {
            log.info("Uploading new movie poster for movie id: " + movieId);
            movie.setPosterUrl(s3FileService.upload(file));
        }

        log.info("Saving movie with updated poster for movie id: " + movieId);
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMoviePoster(Long movieId) throws MovieNotFoundException {
        log.info("Deleting movie poster for movie id: " + movieId);

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + movieId + " not found"));

        String moviePosterURL = movie.getPosterUrl();
        if (moviePosterURL != null) {
            log.info("Deleting movie poster from S3 for movie id: " + movieId);
            s3FileService.delete(moviePosterURL);
            movie.setPosterUrl(null);

            log.info("Saving movie without poster for movie id: " + movieId);
            movieRepository.save(movie);
        }
    }

    @Override
    public byte[] getMoviePoster(Long movieId) throws MovieNotFoundException {
        log.info("Getting movie poster for movie id: " + movieId);

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + movieId + " not found"));

        String moviePosterURL = movie.getPosterUrl();
        if (moviePosterURL != null) {
            log.info("Downloading movie poster from S3 for movie id: " + movieId);
            return s3FileService.download(moviePosterURL);
        } else {
            log.info("No movie poster found for movie id: " + movieId);
            return null;
        }
    }

    @Override
    public Movie addGenreToMovie(Long movieId, Long genreId) throws MovieNotFoundException, GenreNotFoundException {
        log.info("Adding genre id: " + genreId + " to movie id: " + movieId);

        Movie movie = findMovieById(movieId);
        Genre genre = genreService.findGenreById(genreId);
        movie.getGenres().add(genre);

        log.info("Saving movie with added genre for movie id: " + movieId);
        return movieRepository.save(movie);
    }

    @Override
    public Movie removeGenreFromMovie(Long movieId, Long genreId) throws MovieNotFoundException, GenreNotFoundException {
        log.info("Removing genre id: " + genreId + " from movie id: " + movieId);

        Movie movie = findMovieById(movieId);
        Genre genre = genreService.findGenreById(genreId);
        movie.getGenres().remove(genre);

        log.info("Saving movie with removed genre for movie id: " + movieId);
        return movieRepository.save(movie);
    }

}
