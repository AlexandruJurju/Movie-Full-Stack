package com.example.springmovie;

import com.example.springmovie.enums.ReleaseStatus;
import com.example.springmovie.model.Actor;
import com.example.springmovie.model.CastMember;
import com.example.springmovie.model.Genre;
import com.example.springmovie.model.Movie;
import com.example.springmovie.service.ActorService;
import com.example.springmovie.service.GenreService;
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

        actorService.saveActor(new Actor(0L, "Carl Weathers", Date.valueOf("1969-01-10"), null));
        actorService.saveActor(new Actor(0L, "Arnold Schwarzenegger", Date.valueOf("1934-01-01"), null));

        Movie movie = new Movie();
        movie.setTitle("Predator");
        movie.setTagline("Soon the hunt will begin.");
        movie.setOverview("A team of elite commandos on a secret mission in a Central American jungle come to find themselves hunted by an extraterrestrial warrior.");
        movie.setRuntime(107);
        movie.setReleaseStatus(ReleaseStatus.RELEASED);
        movie.setPosterURL(null);
        movie.setReleaseDate(Date.valueOf("1987-06-12"));

        Set<Genre> predatorGenres = new HashSet<>();
        predatorGenres.add(genreService.findGenreById(1L));
        predatorGenres.add(genreService.findGenreById(5L));
        movie.setGenres(predatorGenres);
        movieService.saveMovie(movie);

        Movie predatorMovie = movieService.findMovieById(1L);
        Actor arnoldActor = actorService.findActorById(1L);

//        CastMember newCastMember = new CastMember();
        //        newCastMember.setActor(arnoldActor);
        //        newCastMember.setRole("Dutch");
        //        newCastMember.setDisplayOrder(1);
        //
        //        predatorMovie.addCastMember(newCastMember);
        //
        //        movieService.saveMovie(predatorMovie);
    }
}
