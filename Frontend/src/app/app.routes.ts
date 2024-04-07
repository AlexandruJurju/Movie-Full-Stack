import {Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {UploadMovieComponent} from "./upload-movie/upload-movie.component";
import {FindMoviesComponent} from "./find-movies/find-movies.component";
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";

export const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'upload-movie', component: UploadMovieComponent},
  {path: 'find-movies', component: FindMoviesComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
];
