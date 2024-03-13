import {Component} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {HeaderComponent} from "./header/header.component";
import {HomeComponent} from "./home/home.component";
import {UploadMovieComponent} from "./upload-movie/upload-movie.component";
import {RouterLink, RouterOutlet} from "@angular/router";
import {FindMoviesComponent} from "./find-movies/find-movies.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    HeaderComponent,
    HomeComponent,
    UploadMovieComponent,
    RouterOutlet,
    RouterLink,
    FindMoviesComponent,
  ]
  ,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  constructor() {
  }

}
