import {Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {UploadMovieComponent} from "./pages/upload-movie/upload-movie.component";
import {FindMoviesComponent} from "./pages/find-movies/find-movies.component";
import {RegisterComponent} from "./pages/register/register.component";
import {LoginComponent} from "./pages/login/login.component";


export const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'upload-movie', component: UploadMovieComponent},
  {path: 'find-movies', component: FindMoviesComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
];
