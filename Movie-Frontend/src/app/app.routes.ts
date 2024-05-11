import {Routes} from '@angular/router';
import {UserRegisterComponent} from "./pages/user-register/user-register.component";
import {UserLoginComponent} from "./pages/user-login/user-login.component";
import {MovieListComponent} from "./pages/movie-list/movie-list.component";
import {HomeComponent} from "./pages/home/home.component";
import {MovieDetailsComponent} from "./pages/movie-details/movie-details.component";
import {MovieEditComponent} from "./pages/movie-edit/movie-edit.component";
import {GenreListComponent} from "./pages/genre-list/genre-list.component";
import {GenreEditComponent} from "./pages/genre-edit/genre-edit.component";
import {ErrorComponent} from "./pages/error/error.component";
import {GenreSaveComponent} from "./pages/genre-save/genre-save.component";


export const routes: Routes = [
  {
    path: 'user-register',
    component: UserRegisterComponent
  },
  {
    path: 'user-login',
    component: UserLoginComponent
  },

  {
    path: 'movie-list',
    component: MovieListComponent
  },
  {
    path: 'movie-details/:id',
    component: MovieDetailsComponent
  },
  {
    path: 'movie-edit/:id',
    component: MovieEditComponent
  },

  {
    path: 'genre-list',
    component: GenreListComponent
  },
  {
    path: 'genre-edit/:id',
    component: GenreEditComponent
  },
  {
    path: 'genre-save',
    component: GenreSaveComponent,
  },

  {path: 'home', component: HomeComponent},

  {path: 'error', component: ErrorComponent},

  {path: '', redirectTo: '/home', pathMatch: 'full'},
];
