package com.example.springmovie;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.model.Actor;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import com.example.springmovie.model.MovieActor;
import com.example.springmovie.service.ActorService;
import com.example.springmovie.service.GenreService;
import com.example.springmovie.service.MovieActorService;
import com.example.springmovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class Runner implements CommandLineRunner {

    private final MovieService movieService;
    private final GenreService genreService;
    private final ActorService actorService;
    private final MovieActorService movieActorService;

    @Override
    public void run(String... args) {
        genreService.saveGenre(new Genre("Horror"));
        genreService.saveGenre(new Genre("Action"));
        genreService.saveGenre(new Genre("Drama"));
        genreService.saveGenre(new Genre("Romance"));
        genreService.saveGenre(new Genre("Sci-Fi"));
        genreService.saveGenre(new Genre("Fantasy"));
        genreService.saveGenre(new Genre("Thriller"));
        genreService.saveGenre(new Genre("Adventure"));

        Actor actor = new Actor();
        actor.setName("Arnold Schwarzenegger");
        actor.setBirthDate(Date.valueOf("1934-01-01"));
        actorService.save(actor);

        Actor actor2 = new Actor();
        actor2.setName("Carl Weathers");
        actor.setBirthDate(Date.valueOf("1969-01-10"));
        actorService.save(actor2);

        Movie movie = new Movie();
        movie.setTitle("Predator");
        movie.setTagline("Soon the hunt will begin.");
        movie.setOverview("A team of elite commandos on a secret mission in a Central American jungle come to find themselves hunted by an extraterrestrial warrior.");
        movie.setRuntime(107);
        movie.setReleaseStatus(ReleaseStatus.RELEASED);
        movie.setPosterURL(null);
        movie.setReleaseDate(Date.valueOf("1987-06-12"));
        movie.setImdbUrl("https://www.imdb.com/title/tt0093773/");
        movie.setPosterURL("a210837a-64bd-4d56-8d9f-d99425bd6068.jpg");

        Set<Genre> predatorGenres = new HashSet<>();
        predatorGenres.add(genreService.findGenreById(1L));
        predatorGenres.add(genreService.findGenreById(5L));
        movie.setGenres(predatorGenres);
        movieService.save(movie);

        MovieActor movieActor = new MovieActor();
        movieActor.setActor(actor);
        movieActor.setMovie(movie);
        movieActor.setRole("Dutch");
        movieActorService.save(movieActor);

        MovieActor movieActor2 = new MovieActor();
        movieActor2.setMovie(movie);
        movieActor2.setActor(actor2);
        movieActor2.setRole("Unknown");
        movieActorService.save(movieActor2);

    }
}
