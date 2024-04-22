import {Routes} from '@angular/router';
import {UserRegisterComponent} from "./pages/user-register/user-register.component";
import {UserLoginComponent} from "./pages/user-login/user-login.component";
import {MovieListComponent} from "./pages/movie-list/movie-list.component";
import {HomeComponent} from "./pages/home/home.component";
import {MovieDetailsComponent} from "./pages/movie-details/movie-details.component";


export const routes: Routes = [
  {path: 'user-register', component: UserRegisterComponent},
  {path: 'user-login', component: UserLoginComponent},

  {path: 'movie-list', component: MovieListComponent},
  {path: 'movie-details/:id', component: MovieDetailsComponent},

  {path: 'home', component: HomeComponent},

  {path: '', redirectTo: '/home', pathMatch: 'full'},
];
