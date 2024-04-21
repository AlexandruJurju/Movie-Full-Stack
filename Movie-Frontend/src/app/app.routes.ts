import {Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {UploadMovieComponent} from "./pages/movie-upload/upload-movie.component";
import {MovieFindComponent} from "./pages/movie-find/movie-find.component";
import {UserRegisterComponent} from "./pages/user-register/user-register.component";
import {UserLoginComponent} from "./pages/user-login/user-login.component";


export const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'movie-upload', component: UploadMovieComponent},
  {path: 'movie-find', component: MovieFindComponent},
  {path: 'user-register', component: UserRegisterComponent},
  {path: 'user-login', component: UserLoginComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
];
