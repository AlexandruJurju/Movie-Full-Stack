package com.example.springmovie.controller;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.model.Actor;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import com.example.springmovie.model.MovieActor;
import com.example.springmovie.service.ActorService;
import com.example.springmovie.service.GenreService;
import com.example.springmovie.service.MovieActorService;
import com.example.springmovie.service.MovieService;
import com.example.springmovie.service.impl.file_service.ImageServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Slf4j

@Tag(name = "Movie Controller", description = "CRUD REST APIs for managing movies")

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;
    private final GenreService genreService;
    private final ImageServiceImpl imageServiceImpl;
    private final MovieActorService movieActorService;
    private final ActorService actorService;


    @GetMapping("")
    @Operation(summary = "Get all movies", description = "Retrieve a list of all movies")
    public List<Movie> findAllMovies() {
        //        if (movies.isEmpty()) {
        //            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //        }
        return movieService.saveAll();
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "Get a single movie using id", description = "Retrieve a single movie using an ID passed as a variable")
    public Movie findMovieById(@Parameter(description = "id of movie to be searched") @PathVariable(value = "movieId") Long movieId) {
        return movieService.findById(movieId);
    }

    //    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //    @Operation(summary = "Save a movie", description = "REST API to save a movie based using RequestBody")
    //    public ResponseEntity<Movie> saveMovie(Movie movie, @RequestParam(value = "file") MultipartFile file) {
    //        String path = imageService.upload(file);
    //        movie.setPosterURL(path);
    //        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.CREATED);
    //    }

    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping
    @Operation(summary = "Update a Movie", description = "REST API to update a Movie based using RequestBody")
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @DeleteMapping("/{movieId}")
    @Operation(summary = "Delete a Movie", description = "REST API to delete a Movie using an id passed as a variable")
    public void deleteMovie(@PathVariable("movieId") Long movieId) {
        movieService.deleteById(movieId);
    }

    @GetMapping("status/{releaseStatus}")
    @Operation(summary = "Find movies by release status")
    public List<Movie> findMoviesByReleaseStatus(@PathVariable("releaseStatus") ReleaseStatus status) {
        return movieService.findByReleaseStatus(status);
    }

    @GetMapping("/{movieId}/genre")
    @Operation(summary = "Find all genres of a movie")
    public List<Genre> findAllGenresOfAMovie(@PathVariable("movieId") Long movieId) {
        return movieService.findAllGenresOfMovie(movieId);
    }

    @GetMapping("/findByGenreId/{genreId}")
    @Operation(summary = "Find all movies that contain a genre using the genreId")
    public List<Movie> findAllMoviesWithGenreID(@PathVariable("genreId") Long genreId) {
        return movieService.findByGenreId(genreId);
    }

    @GetMapping("/findByGenreName/{genreName}")
    public List<Movie> findAllMoviesWithGenreName(@PathVariable("genreName") String genreName) {
        return movieService.findByGenreName(genreName);
    }

    @PutMapping("/{movieId}/addGenre/{genreId}")
    @Operation(summary = "Add a genre to a movie")
    public Movie addGenreToMovie(@Parameter(description = "id of movie that the genre will be added to") @PathVariable("movieId") Long movieId,
                                 @Parameter(description = "id of the genre that will be added to the movie") @PathVariable("genreId") Long genreId) {
        Genre genre = genreService.findGenreById(genreId);
        Movie movie = movieService.findById(movieId);
        movie.getGenres().add(genre);
        return movieService.save(movie);
    }

    @PutMapping("/{movieId}/removeGenre/{genreId}")
    @Operation(summary = "Remove a genre from a movie")
    public Movie removeGenreFromMovie(@PathVariable("movieId") Long movieId, @PathVariable("genreId") Long genreId) {
        Genre genre = genreService.findGenreById(genreId);
        //        if (genre == null) {
        //            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //        }

        Movie movie = movieService.findById(movieId);
        movie.getGenres().remove(genre);
        return movieService.save(movie);
    }

    @PutMapping("/{movieId}/poster/delete")
    @Operation(summary = "Delete a poster from a movie")
    public Movie deletePoster(@PathVariable("movieId") Long movieId) {
        Movie movie = movieService.findById(movieId);
        String moviePosterURL = movie.getPosterURL();
        if (moviePosterURL != null) {
            imageServiceImpl.delete(moviePosterURL);
        }
        movie.setPosterURL(null);
        return movieService.save(movie);
    }

    @GetMapping("/{movieId}/poster")
    @Operation(summary = "Get the poster image from a movie")
    public byte[] getMoviePoster(@PathVariable("movieId") Long movieId) {
        log.info("STARTING");
        Movie movie = movieService.findById(movieId);
        log.info(movie.getPosterURL());
        //        byte[] image = imageServiceImpl.download(movie.getPosterURL());
        //        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
        return imageServiceImpl.download(movie.getPosterURL());
    }

    @PostMapping(value = "/poster", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update a poster of a movie")
    public Movie updateMoviePoster(@RequestParam("movieId") Long movieId, @RequestParam(value = "file") MultipartFile file) {
        Movie movie = movieService.findById(movieId);
        String moviePosterURL = movie.getPosterURL();
        if (moviePosterURL != null) {
            imageServiceImpl.delete(moviePosterURL);
        }
        String newPath = imageServiceImpl.upload(file);
        movie.setPosterURL(newPath);
        return movieService.save(movie);
    }

    @GetMapping("/year/{year}")
    @Operation(summary = "Get all movies that were released in a year")
    public List<Movie> getMoviesInYear(@PathVariable("year") int year) {
        return movieService.findByYear(year);
    }

    @PostMapping("/addActor")
    @Operation(summary = "Add an actor to a a movie")
    public MovieActor addActorToMovie(@RequestBody MovieActor movieCast) {
        Movie movie = movieService.findById(movieCast.getMovie().getId());
        Actor actor = actorService.findById(movieCast.getActor().getId());

        movieCast.setMovie(movie);
        movieCast.setActor(actor);

        movieActorService.save(movieCast);

        return movieCast;
    }

    @DeleteMapping("/{movieId}/removeActor/{actorId}")
    @Operation(summary = "Remove actor from movie")
    public void removeActorFromMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        List<MovieActor> movieCasts = movieActorService.findAll();

        for (MovieActor movieCast : movieCasts) {
            if (movieCast.getMovie().getId().equals(movieId) && movieCast.getActor().getId().equals(actorId)) {
                movieActorService.deleteById(movieCast.getId());
            }
        }
    }

    @GetMapping("/{movieId}/actors")
    @Operation(summary = "Get all actors from a movie")
    public List<MovieActor> getActorsByMovieId(@PathVariable Long movieId) {

        return movieActorService.findAllByMovieId(movieId);
    }
}
