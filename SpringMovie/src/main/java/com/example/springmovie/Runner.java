package com.example.springmovie;

import com.example.springmovie.repositories.UserRepository;
import com.example.springmovie.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Runner implements CommandLineRunner {

    private final MovieService movieService;
    private final GenreService genreService;
    private final ActorService actorService;
    private final MovieActorService movieActorService;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        //        genreService.saveGenre(new Genre("Horror"));
        //        genreService.saveGenre(new Genre("Action"));
        //        genreService.saveGenre(new Genre("Drama"));
        //        genreService.saveGenre(new Genre("Romance"));
        //        genreService.saveGenre(new Genre("Sci-Fi"));
        //        genreService.saveGenre(new Genre("Fantasy"));
        //        genreService.saveGenre(new Genre("Thriller"));
        //        genreService.saveGenre(new Genre("Adventure"));
        //
        //        Actor actor = new Actor();
        //        actor.setName("Arnold Schwarzenegger");
        //        actor.setBirthDate(LocalDate.of(1947, 7, 30));
        //        actorService.saveActor(actor);
        //
        //        Actor actor2 = new Actor();
        //        actor2.setName("Carl Weathers");
        //        actor.setBirthDate(LocalDate.of(1948, 2, 14));
        //        actorService.saveActor(actor2);
        //
        //        Movie movie = new Movie();
        //        movie.setTitle("Predator");
        //        movie.setTagline("Soon the hunt will begin.");
        //        movie.setOverview("A team of elite commandos on a secret mission in a Central American jungle come to find themselves hunted by an extraterrestrial
        //        warrior.");
        //        movie.setRuntime(107);
        //        movie.setReleaseStatus(ReleaseStatus.RELEASED);
        //        movie.setPosterURL(null);
        //        movie.setReleaseDate(LocalDate.of(1987, 6, 12));
        //        movie.setImdbUrl("https://www.imdb.com/title/tt0093773/");
        //            movie.setPosterURL("09eeba02-e2ec-4df1-98d8-2579157edf5d.jpg");
        //
        //        Set<Genre> predatorGenres = new HashSet<>();
        //        predatorGenres.add(genreService.findGenreById(1L));
        //        predatorGenres.add(genreService.findGenreById(5L));
        //        movie.setGenres(predatorGenres);
        //        movieService.saveMovie(movie);
        //
        //        MovieActor movieActor = new MovieActor();
        //        movieActor.setActor(actor);
        //        movieActor.setMovie(movie);
        //        movieActor.setRole("Dutch");
        //        movieActorService.saveMovieActor(movieActor);
        //
        //        MovieActor movieActor2 = new MovieActor();
        //        movieActor2.setMovie(movie);
        //        movieActor2.setActor(actor2);
        //        movieActor2.setRole("Unknown");
        //        movieActorService.saveMovieActor(movieActor2);

        //        UserRegisterRequest request = new UserRegisterRequest("alex","alex@gmail.com","dapassword");
        //        try {
        //            userService.registerUser(request);
        //        } catch (UserAlreadyExistsException e) {
        //            throw new RuntimeException(e);
        //        }

//        List<User> users = userRepository.findUserByRole(Role.USER);
//        if (users.isEmpty()) {
//            User user = User
//                    .builder()
//                    .username("thebigman123")
//                    .email("thebigman@gmail.com")
//                    .password("thebigman123")
//                    .role(Role.USER)
//                    .build();
//            userRepository.save(user);
//        }

    }
}
