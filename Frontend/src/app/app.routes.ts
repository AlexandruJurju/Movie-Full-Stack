import {Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {UploadMovieComponent} from "./upload-movie/upload-movie.component";

export const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'upload-movie', component: UploadMovieComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
];
