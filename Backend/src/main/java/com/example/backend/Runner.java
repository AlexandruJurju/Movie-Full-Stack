package com.example.backend;

import com.example.backend.model.Genre;
import com.example.backend.model.Movie;
import com.example.backend.services.GenreService;
import com.example.backend.services.MovieService;
import com.example.backend.utility.enums.ReleaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class Runner implements CommandLineRunner {

    private final MovieService movieService;
    private final GenreService genreService;


    @Override
    public void run(String... args) throws Exception {
        genreService.saveGenre(new Genre("Horror"));
        genreService.saveGenre(new Genre("Action"));
        genreService.saveGenre(new Genre("Drama"));
        genreService.saveGenre(new Genre("Romance"));
        genreService.saveGenre(new Genre("Sci-Fi"));
        genreService.saveGenre(new Genre("Fantasy"));
        genreService.saveGenre(new Genre("Thriller"));
        genreService.saveGenre(new Genre("Adventure"));


        Movie movie = new Movie();
        movie.setTitle("Alien");
        movie.setTagline("In space no one can hear you scream");
        movie.setOverview("During its return to the earth, commercial spaceship Nostromo intercepts a distress signal from a distant planet. When a three-member team of the" +
                " crew discovers a chamber containing thousands of eggs on the planet , a creature inside one of the eggs attacks an explorer. The entire crew is unaware of" +
                " the impending nightmare set to descend upon them when the alien parasite planted inside its unfortunate host is birthed.");
        movie.setRuntime(117);
        movie.setRevenue(104);
        movie.setBudget(11);
        movie.setReleaseStatus(ReleaseStatus.RELEASED);
        movie.setImageURL(null);
        movie.setReleaseDate(Date.valueOf("1979-05-25"));
        Set<Genre> alienGenres = new HashSet<>();
        alienGenres.add(genreService.findGenreById(1L));
        alienGenres.add(genreService.findGenreById(5L));
        movie.setGenres(alienGenres);
        movieService.saveMovie(movie);

        movie = new Movie();
        movie.setTitle("Predator");
        movie.setTagline("Soon the hunt will begin.");
        movie.setOverview("A team of elite commandos on a secret mission in a Central American jungle come to find themselves hunted by an extraterrestrial warrior.");
        movie.setRuntime(107);
        movie.setRevenue(98);
        movie.setBudget(15);
        movie.setReleaseStatus(ReleaseStatus.RELEASED);
        movie.setImageURL(null);
        movie.setReleaseDate(Date.valueOf("1987-06-12"));
        movieService.saveMovie(movie);
    }
}
