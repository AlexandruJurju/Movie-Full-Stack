export * from './actor.service';
import {ActorService} from './actor.service';
import {AuthenticationService} from './authentication.service';
import {GenreService} from './genre.service';
import {MovieActorService} from './movie-actor.service';
import {MovieService} from './movie.service';
import {UserService} from './user.service';

export * from './authentication.service';

export * from './genre.service';

export * from './movie-actor.service';

export * from './movie.service';

export * from './user.service';

export const APIS = [ActorService, AuthenticationService, GenreService, MovieActorService, MovieService, UserService];
